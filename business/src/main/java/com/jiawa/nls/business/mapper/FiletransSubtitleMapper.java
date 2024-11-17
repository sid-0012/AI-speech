package com.jiawa.nls.business.mapper;

import com.jiawa.nls.business.domain.FiletransSubtitle;
import com.jiawa.nls.business.domain.FiletransSubtitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FiletransSubtitleMapper {
    long countByExample(FiletransSubtitleExample example);

    int deleteByExample(FiletransSubtitleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FiletransSubtitle record);

    int insertSelective(FiletransSubtitle record);

    List<FiletransSubtitle> selectByExample(FiletransSubtitleExample example);

    FiletransSubtitle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FiletransSubtitle record, @Param("example") FiletransSubtitleExample example);

    int updateByExample(@Param("record") FiletransSubtitle record, @Param("example") FiletransSubtitleExample example);

    int updateByPrimaryKeySelective(FiletransSubtitle record);

    int updateByPrimaryKey(FiletransSubtitle record);
}