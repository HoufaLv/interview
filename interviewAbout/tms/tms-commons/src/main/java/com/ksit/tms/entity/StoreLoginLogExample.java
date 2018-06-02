package com.ksit.tms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreLoginLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreLoginLogExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLogIpIsNull() {
            addCriterion("log_ip is null");
            return (Criteria) this;
        }

        public Criteria andLogIpIsNotNull() {
            addCriterion("log_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLogIpEqualTo(String value) {
            addCriterion("log_ip =", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpNotEqualTo(String value) {
            addCriterion("log_ip <>", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpGreaterThan(String value) {
            addCriterion("log_ip >", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpGreaterThanOrEqualTo(String value) {
            addCriterion("log_ip >=", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpLessThan(String value) {
            addCriterion("log_ip <", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpLessThanOrEqualTo(String value) {
            addCriterion("log_ip <=", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpLike(String value) {
            addCriterion("log_ip like", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpNotLike(String value) {
            addCriterion("log_ip not like", value, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpIn(List<String> values) {
            addCriterion("log_ip in", values, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpNotIn(List<String> values) {
            addCriterion("log_ip not in", values, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpBetween(String value1, String value2) {
            addCriterion("log_ip between", value1, value2, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogIpNotBetween(String value1, String value2) {
            addCriterion("log_ip not between", value1, value2, "logIp");
            return (Criteria) this;
        }

        public Criteria andLogTimeIsNull() {
            addCriterion("log_time is null");
            return (Criteria) this;
        }

        public Criteria andLogTimeIsNotNull() {
            addCriterion("log_time is not null");
            return (Criteria) this;
        }

        public Criteria andLogTimeEqualTo(Date value) {
            addCriterion("log_time =", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotEqualTo(Date value) {
            addCriterion("log_time <>", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeGreaterThan(Date value) {
            addCriterion("log_time >", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("log_time >=", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeLessThan(Date value) {
            addCriterion("log_time <", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeLessThanOrEqualTo(Date value) {
            addCriterion("log_time <=", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeIn(List<Date> values) {
            addCriterion("log_time in", values, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotIn(List<Date> values) {
            addCriterion("log_time not in", values, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeBetween(Date value1, Date value2) {
            addCriterion("log_time between", value1, value2, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotBetween(Date value1, Date value2) {
            addCriterion("log_time not between", value1, value2, "logTime");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolIsNull() {
            addCriterion("store_login_logcol is null");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolIsNotNull() {
            addCriterion("store_login_logcol is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolEqualTo(String value) {
            addCriterion("store_login_logcol =", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolNotEqualTo(String value) {
            addCriterion("store_login_logcol <>", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolGreaterThan(String value) {
            addCriterion("store_login_logcol >", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolGreaterThanOrEqualTo(String value) {
            addCriterion("store_login_logcol >=", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolLessThan(String value) {
            addCriterion("store_login_logcol <", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolLessThanOrEqualTo(String value) {
            addCriterion("store_login_logcol <=", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolLike(String value) {
            addCriterion("store_login_logcol like", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolNotLike(String value) {
            addCriterion("store_login_logcol not like", value, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolIn(List<String> values) {
            addCriterion("store_login_logcol in", values, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolNotIn(List<String> values) {
            addCriterion("store_login_logcol not in", values, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolBetween(String value1, String value2) {
            addCriterion("store_login_logcol between", value1, value2, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreLoginLogcolNotBetween(String value1, String value2) {
            addCriterion("store_login_logcol not between", value1, value2, "storeLoginLogcol");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdIsNull() {
            addCriterion("store_account_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdIsNotNull() {
            addCriterion("store_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdEqualTo(Integer value) {
            addCriterion("store_account_id =", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdNotEqualTo(Integer value) {
            addCriterion("store_account_id <>", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdGreaterThan(Integer value) {
            addCriterion("store_account_id >", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_account_id >=", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdLessThan(Integer value) {
            addCriterion("store_account_id <", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_account_id <=", value, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdIn(List<Integer> values) {
            addCriterion("store_account_id in", values, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdNotIn(List<Integer> values) {
            addCriterion("store_account_id not in", values, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("store_account_id between", value1, value2, "storeAccountId");
            return (Criteria) this;
        }

        public Criteria andStoreAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_account_id not between", value1, value2, "storeAccountId");
            return (Criteria) this;
        }
    }

    /**
     */
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