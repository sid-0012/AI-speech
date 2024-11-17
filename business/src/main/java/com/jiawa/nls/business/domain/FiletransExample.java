package com.jiawa.nls.business.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiletransExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FiletransExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSecondIsNull() {
            addCriterion("`second` is null");
            return (Criteria) this;
        }

        public Criteria andSecondIsNotNull() {
            addCriterion("`second` is not null");
            return (Criteria) this;
        }

        public Criteria andSecondEqualTo(Integer value) {
            addCriterion("`second` =", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotEqualTo(Integer value) {
            addCriterion("`second` <>", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThan(Integer value) {
            addCriterion("`second` >", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThanOrEqualTo(Integer value) {
            addCriterion("`second` >=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThan(Integer value) {
            addCriterion("`second` <", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThanOrEqualTo(Integer value) {
            addCriterion("`second` <=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondIn(List<Integer> values) {
            addCriterion("`second` in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotIn(List<Integer> values) {
            addCriterion("`second` not in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondBetween(Integer value1, Integer value2) {
            addCriterion("`second` between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotBetween(Integer value1, Integer value2) {
            addCriterion("`second` not between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAudioIsNull() {
            addCriterion("audio is null");
            return (Criteria) this;
        }

        public Criteria andAudioIsNotNull() {
            addCriterion("audio is not null");
            return (Criteria) this;
        }

        public Criteria andAudioEqualTo(String value) {
            addCriterion("audio =", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotEqualTo(String value) {
            addCriterion("audio <>", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioGreaterThan(String value) {
            addCriterion("audio >", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioGreaterThanOrEqualTo(String value) {
            addCriterion("audio >=", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioLessThan(String value) {
            addCriterion("audio <", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioLessThanOrEqualTo(String value) {
            addCriterion("audio <=", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioLike(String value) {
            addCriterion("audio like", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotLike(String value) {
            addCriterion("audio not like", value, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioIn(List<String> values) {
            addCriterion("audio in", values, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotIn(List<String> values) {
            addCriterion("audio not in", values, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioBetween(String value1, String value2) {
            addCriterion("audio between", value1, value2, "audio");
            return (Criteria) this;
        }

        public Criteria andAudioNotBetween(String value1, String value2) {
            addCriterion("audio not between", value1, value2, "audio");
            return (Criteria) this;
        }

        public Criteria andFileSignIsNull() {
            addCriterion("file_sign is null");
            return (Criteria) this;
        }

        public Criteria andFileSignIsNotNull() {
            addCriterion("file_sign is not null");
            return (Criteria) this;
        }

        public Criteria andFileSignEqualTo(String value) {
            addCriterion("file_sign =", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignNotEqualTo(String value) {
            addCriterion("file_sign <>", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignGreaterThan(String value) {
            addCriterion("file_sign >", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignGreaterThanOrEqualTo(String value) {
            addCriterion("file_sign >=", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignLessThan(String value) {
            addCriterion("file_sign <", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignLessThanOrEqualTo(String value) {
            addCriterion("file_sign <=", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignLike(String value) {
            addCriterion("file_sign like", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignNotLike(String value) {
            addCriterion("file_sign not like", value, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignIn(List<String> values) {
            addCriterion("file_sign in", values, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignNotIn(List<String> values) {
            addCriterion("file_sign not in", values, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignBetween(String value1, String value2) {
            addCriterion("file_sign between", value1, value2, "fileSign");
            return (Criteria) this;
        }

        public Criteria andFileSignNotBetween(String value1, String value2) {
            addCriterion("file_sign not between", value1, value2, "fileSign");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(String value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(String value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(String value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(String value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(String value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLike(String value) {
            addCriterion("pay_status like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotLike(String value) {
            addCriterion("pay_status not like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<String> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<String> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(String value1, String value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(String value1, String value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLangIsNull() {
            addCriterion("lang is null");
            return (Criteria) this;
        }

        public Criteria andLangIsNotNull() {
            addCriterion("lang is not null");
            return (Criteria) this;
        }

        public Criteria andLangEqualTo(String value) {
            addCriterion("lang =", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotEqualTo(String value) {
            addCriterion("lang <>", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangGreaterThan(String value) {
            addCriterion("lang >", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangGreaterThanOrEqualTo(String value) {
            addCriterion("lang >=", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLessThan(String value) {
            addCriterion("lang <", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLessThanOrEqualTo(String value) {
            addCriterion("lang <=", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLike(String value) {
            addCriterion("lang like", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotLike(String value) {
            addCriterion("lang not like", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangIn(List<String> values) {
            addCriterion("lang in", values, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotIn(List<String> values) {
            addCriterion("lang not in", values, "lang");
            return (Criteria) this;
        }

        public Criteria andLangBetween(String value1, String value2) {
            addCriterion("lang between", value1, value2, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotBetween(String value1, String value2) {
            addCriterion("lang not between", value1, value2, "lang");
            return (Criteria) this;
        }

        public Criteria andVodIsNull() {
            addCriterion("vod is null");
            return (Criteria) this;
        }

        public Criteria andVodIsNotNull() {
            addCriterion("vod is not null");
            return (Criteria) this;
        }

        public Criteria andVodEqualTo(String value) {
            addCriterion("vod =", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodNotEqualTo(String value) {
            addCriterion("vod <>", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodGreaterThan(String value) {
            addCriterion("vod >", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodGreaterThanOrEqualTo(String value) {
            addCriterion("vod >=", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodLessThan(String value) {
            addCriterion("vod <", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodLessThanOrEqualTo(String value) {
            addCriterion("vod <=", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodLike(String value) {
            addCriterion("vod like", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodNotLike(String value) {
            addCriterion("vod not like", value, "vod");
            return (Criteria) this;
        }

        public Criteria andVodIn(List<String> values) {
            addCriterion("vod in", values, "vod");
            return (Criteria) this;
        }

        public Criteria andVodNotIn(List<String> values) {
            addCriterion("vod not in", values, "vod");
            return (Criteria) this;
        }

        public Criteria andVodBetween(String value1, String value2) {
            addCriterion("vod between", value1, value2, "vod");
            return (Criteria) this;
        }

        public Criteria andVodNotBetween(String value1, String value2) {
            addCriterion("vod not between", value1, value2, "vod");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeIsNull() {
            addCriterion("trans_status_code is null");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeIsNotNull() {
            addCriterion("trans_status_code is not null");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeEqualTo(Integer value) {
            addCriterion("trans_status_code =", value, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeNotEqualTo(Integer value) {
            addCriterion("trans_status_code <>", value, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeGreaterThan(Integer value) {
            addCriterion("trans_status_code >", value, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trans_status_code >=", value, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeLessThan(Integer value) {
            addCriterion("trans_status_code <", value, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeLessThanOrEqualTo(Integer value) {
            addCriterion("trans_status_code <=", value, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeIn(List<Integer> values) {
            addCriterion("trans_status_code in", values, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeNotIn(List<Integer> values) {
            addCriterion("trans_status_code not in", values, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeBetween(Integer value1, Integer value2) {
            addCriterion("trans_status_code between", value1, value2, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("trans_status_code not between", value1, value2, "transStatusCode");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextIsNull() {
            addCriterion("trans_status_text is null");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextIsNotNull() {
            addCriterion("trans_status_text is not null");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextEqualTo(String value) {
            addCriterion("trans_status_text =", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextNotEqualTo(String value) {
            addCriterion("trans_status_text <>", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextGreaterThan(String value) {
            addCriterion("trans_status_text >", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextGreaterThanOrEqualTo(String value) {
            addCriterion("trans_status_text >=", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextLessThan(String value) {
            addCriterion("trans_status_text <", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextLessThanOrEqualTo(String value) {
            addCriterion("trans_status_text <=", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextLike(String value) {
            addCriterion("trans_status_text like", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextNotLike(String value) {
            addCriterion("trans_status_text not like", value, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextIn(List<String> values) {
            addCriterion("trans_status_text in", values, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextNotIn(List<String> values) {
            addCriterion("trans_status_text not in", values, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextBetween(String value1, String value2) {
            addCriterion("trans_status_text between", value1, value2, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransStatusTextNotBetween(String value1, String value2) {
            addCriterion("trans_status_text not between", value1, value2, "transStatusText");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNull() {
            addCriterion("trans_time is null");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNotNull() {
            addCriterion("trans_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransTimeEqualTo(Date value) {
            addCriterion("trans_time =", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotEqualTo(Date value) {
            addCriterion("trans_time <>", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThan(Date value) {
            addCriterion("trans_time >", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trans_time >=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThan(Date value) {
            addCriterion("trans_time <", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThanOrEqualTo(Date value) {
            addCriterion("trans_time <=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeIn(List<Date> values) {
            addCriterion("trans_time in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotIn(List<Date> values) {
            addCriterion("trans_time not in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeBetween(Date value1, Date value2) {
            addCriterion("trans_time between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotBetween(Date value1, Date value2) {
            addCriterion("trans_time not between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeIsNull() {
            addCriterion("solve_time is null");
            return (Criteria) this;
        }

        public Criteria andSolveTimeIsNotNull() {
            addCriterion("solve_time is not null");
            return (Criteria) this;
        }

        public Criteria andSolveTimeEqualTo(Date value) {
            addCriterion("solve_time =", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotEqualTo(Date value) {
            addCriterion("solve_time <>", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeGreaterThan(Date value) {
            addCriterion("solve_time >", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("solve_time >=", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeLessThan(Date value) {
            addCriterion("solve_time <", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeLessThanOrEqualTo(Date value) {
            addCriterion("solve_time <=", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeIn(List<Date> values) {
            addCriterion("solve_time in", values, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotIn(List<Date> values) {
            addCriterion("solve_time not in", values, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeBetween(Date value1, Date value2) {
            addCriterion("solve_time between", value1, value2, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotBetween(Date value1, Date value2) {
            addCriterion("solve_time not between", value1, value2, "solveTime");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}