package com.jiawa.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.nls.business.domain.FiletransSubtitle;
import com.jiawa.nls.business.domain.FiletransSubtitleExample;
import com.jiawa.nls.business.mapper.FiletransSubtitleMapper;
import com.jiawa.nls.business.req.FiletransSubtitleQueryReq;
import com.jiawa.nls.business.req.GenSubtitleReq;
import com.jiawa.nls.business.req.GenTextReq;
import com.jiawa.nls.business.resp.FiletransSubtitleQueryResp;
import com.jiawa.nls.business.resp.PageResp;
import com.jiawa.nls.business.util.VodUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Service
public class FiletransSubtitleService {

    @Value("${temp.dir}")
    private String tempDir;

    @Resource
    FiletransSubtitleMapper filetransSubtitleMapper;

    @Transactional
    public void saveSubtitle(Long filetransId, JSONObject result) {
        Date now = new Date();
        // 先清空
        FiletransSubtitleExample filetransSubtitleExample = new FiletransSubtitleExample();
        filetransSubtitleExample.createCriteria().andFiletransIdEqualTo(filetransId);
        filetransSubtitleMapper.deleteByExample(filetransSubtitleExample);

        JSONArray sentences = result.getJSONArray("Sentences");
        for (int i = 0; i < sentences.size(); i++) {
            JSONObject sentence = (JSONObject) sentences.get(i);
            String text = sentence.getString("Text");
            Integer beginTime = sentence.getInteger("BeginTime");
            Integer endTime = sentence.getInteger("EndTime");

            FiletransSubtitle filetransSubtitle = new FiletransSubtitle();
            filetransSubtitle.setId(IdUtil.getSnowflakeNextId());
            filetransSubtitle.setFiletransId(filetransId);
            filetransSubtitle.setIndex(i+1);
            filetransSubtitle.setBegin(beginTime);
            filetransSubtitle.setEnd(endTime);
            filetransSubtitle.setText(text);
            filetransSubtitle.setCreatedAt(now);
            filetransSubtitle.setUpdatedAt(now);
            filetransSubtitleMapper.insert(filetransSubtitle);
        }
    }

    public PageResp<FiletransSubtitleQueryResp> query(FiletransSubtitleQueryReq req) {
        FiletransSubtitleExample filetransSubtitleExample = new FiletransSubtitleExample();
        FiletransSubtitleExample.Criteria criteria = filetransSubtitleExample.createCriteria();

        criteria.andFiletransIdEqualTo(req.getFiletransId());

        filetransSubtitleExample.setOrderByClause("`index` asc");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<FiletransSubtitle> filetransSubtitleList = filetransSubtitleMapper.selectByExample(filetransSubtitleExample);
        // 构造分页返回信息
        PageResp<FiletransSubtitleQueryResp> pageResp = new PageResp<>();
        // 获取分页信息，只需要总数
        PageInfo<FiletransSubtitle> pageInfo = new PageInfo<>(filetransSubtitleList);
        pageResp.setTotal(pageInfo.getTotal());
        // 获取当前分页列表内容
        List<FiletransSubtitleQueryResp> filetransSubtitleQueryRespList = BeanUtil.copyToList(filetransSubtitleList, FiletransSubtitleQueryResp.class);
        pageResp.setList(filetransSubtitleQueryRespList);

        return pageResp;
    }

    /**
     * 生成字幕文件
     */
    public String genSubtitle(GenSubtitleReq req) {
        String suffix = ".srt";
        Long filetransId = req.getFiletransId();
        log.info("获取字幕");
        FiletransSubtitleExample filetransSubtitleExample = new FiletransSubtitleExample();
        FiletransSubtitleExample.Criteria criteria = filetransSubtitleExample.createCriteria();
        criteria.andFiletransIdEqualTo(req.getFiletransId());
        List<FiletransSubtitle> filetransSubtitleList = filetransSubtitleMapper.selectByExample(filetransSubtitleExample);

        log.info("格式化字幕");
        StringBuffer buffer = this.formatSubtitle(filetransSubtitleList);

        return uploadFile(filetransId, buffer, suffix);
    }

    /**
     * 生成纯文本文件
     */
    public String genText(GenTextReq req) {
        String suffix = ".txt";
        Long filetransId = req.getFiletransId();
        log.info("获取文本");
        FiletransSubtitleExample filetransSubtitleExample = new FiletransSubtitleExample();
        FiletransSubtitleExample.Criteria criteria = filetransSubtitleExample.createCriteria();
        criteria.andFiletransIdEqualTo(req.getFiletransId());
        List<FiletransSubtitle> filetransSubtitleList = filetransSubtitleMapper.selectByExample(filetransSubtitleExample);

        log.info("格式化文本");
        StringBuffer buffer = this.formatText(filetransSubtitleList);

        return uploadFile(filetransId, buffer, suffix);
    }

    private String uploadFile(Long filetransId, StringBuffer buffer, String suffix) {
        String fullPath = tempDir + filetransId + suffix;
        log.info("生成临时文件：{}", fullPath);

        FileUtil.mkdir(tempDir);
        FileUtil.writeBytes(buffer.toString().getBytes(), fullPath);

        String url = VodUtil.uploadSubtitle(fullPath);
        log.info("上传临时文件成功：{}", url);

        log.info("删除临时文件：{}", fullPath);
        FileUtil.del(fullPath);

        return url;
    }

    /**
     * 格式化字幕
     */
    private StringBuffer formatSubtitle(List<FiletransSubtitle> list) {
        log.info("拼接字幕数据，总行数：{}", list.size());
        StringBuffer buffer = new StringBuffer();

        for (int i = 0, l = list.size(); i < l; i++) {
            FiletransSubtitle item = list.get(i);
            buffer.append(i);
            buffer.append(System.getProperty("line.separator"));
            buffer.append(this.convertMs(Long.valueOf(item.getBegin())) + " --> " + this.convertMs(Long.valueOf(item.getEnd())));
            buffer.append(System.getProperty("line.separator"));
            buffer.append(item.getText());
            buffer.append(System.getProperty("line.separator"));
            buffer.append(System.getProperty("line.separator"));
        }
        log.info("拼接字幕完成，字符数：{}", buffer.length());
        return buffer;
    }

    public String convertMs(Long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("ETC/GMT-8"));
        return simpleDateFormat.format(ms);
    }

    /**
     * 格式化纯文本
     */
    private StringBuffer formatText(List<FiletransSubtitle> list) {
        log.info("拼接纯文本数据，总行数：{}", list.size());
        StringBuffer buffer = new StringBuffer();

        for (FiletransSubtitle item : list) {
            buffer.append(item.getText());
            buffer.append(System.getProperty("line.separator"));
        }
        log.info("拼接纯文本完成，字符数：{}", buffer.length());
        return buffer;
    }
}
