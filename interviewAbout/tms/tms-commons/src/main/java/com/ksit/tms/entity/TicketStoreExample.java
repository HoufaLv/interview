package com.ksit.tms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketStoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TicketStoreExample() {
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

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNull() {
            addCriterion("store_address is null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNotNull() {
            addCriterion("store_address is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressEqualTo(String value) {
            addCriterion("store_address =", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotEqualTo(String value) {
            addCriterion("store_address <>", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThan(String value) {
            addCriterion("store_address >", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThanOrEqualTo(String value) {
            addCriterion("store_address >=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThan(String value) {
            addCriterion("store_address <", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThanOrEqualTo(String value) {
            addCriterion("store_address <=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLike(String value) {
            addCriterion("store_address like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotLike(String value) {
            addCriterion("store_address not like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIn(List<String> values) {
            addCriterion("store_address in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotIn(List<String> values) {
            addCriterion("store_address not in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressBetween(String value1, String value2) {
            addCriterion("store_address between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotBetween(String value1, String value2) {
            addCriterion("store_address not between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreTelIsNull() {
            addCriterion("store_tel is null");
            return (Criteria) this;
        }

        public Criteria andStoreTelIsNotNull() {
            addCriterion("store_tel is not null");
            return (Criteria) this;
        }

        public Criteria andStoreTelEqualTo(String value) {
            addCriterion("store_tel =", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelNotEqualTo(String value) {
            addCriterion("store_tel <>", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelGreaterThan(String value) {
            addCriterion("store_tel >", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelGreaterThanOrEqualTo(String value) {
            addCriterion("store_tel >=", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelLessThan(String value) {
            addCriterion("store_tel <", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelLessThanOrEqualTo(String value) {
            addCriterion("store_tel <=", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelLike(String value) {
            addCriterion("store_tel like", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelNotLike(String value) {
            addCriterion("store_tel not like", value, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelIn(List<String> values) {
            addCriterion("store_tel in", values, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelNotIn(List<String> values) {
            addCriterion("store_tel not in", values, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelBetween(String value1, String value2) {
            addCriterion("store_tel between", value1, value2, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreTelNotBetween(String value1, String value2) {
            addCriterion("store_tel not between", value1, value2, "storeTel");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIsNull() {
            addCriterion("store_manager is null");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIsNotNull() {
            addCriterion("store_manager is not null");
            return (Criteria) this;
        }

        public Criteria andStoreManagerEqualTo(String value) {
            addCriterion("store_manager =", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerNotEqualTo(String value) {
            addCriterion("store_manager <>", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerGreaterThan(String value) {
            addCriterion("store_manager >", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerGreaterThanOrEqualTo(String value) {
            addCriterion("store_manager >=", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerLessThan(String value) {
            addCriterion("store_manager <", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerLessThanOrEqualTo(String value) {
            addCriterion("store_manager <=", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerLike(String value) {
            addCriterion("store_manager like", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerNotLike(String value) {
            addCriterion("store_manager not like", value, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIn(List<String> values) {
            addCriterion("store_manager in", values, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerNotIn(List<String> values) {
            addCriterion("store_manager not in", values, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerBetween(String value1, String value2) {
            addCriterion("store_manager between", value1, value2, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreManagerNotBetween(String value1, String value2) {
            addCriterion("store_manager not between", value1, value2, "storeManager");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeIsNull() {
            addCriterion("store_register_time is null");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeIsNotNull() {
            addCriterion("store_register_time is not null");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeEqualTo(Date value) {
            addCriterion("store_register_time =", value, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeNotEqualTo(Date value) {
            addCriterion("store_register_time <>", value, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeGreaterThan(Date value) {
            addCriterion("store_register_time >", value, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("store_register_time >=", value, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeLessThan(Date value) {
            addCriterion("store_register_time <", value, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeLessThanOrEqualTo(Date value) {
            addCriterion("store_register_time <=", value, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeIn(List<Date> values) {
            addCriterion("store_register_time in", values, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeNotIn(List<Date> values) {
            addCriterion("store_register_time not in", values, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeBetween(Date value1, Date value2) {
            addCriterion("store_register_time between", value1, value2, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreRegisterTimeNotBetween(Date value1, Date value2) {
            addCriterion("store_register_time not between", value1, value2, "storeRegisterTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeIsNull() {
            addCriterion("store_close_time is null");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeIsNotNull() {
            addCriterion("store_close_time is not null");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeEqualTo(Date value) {
            addCriterion("store_close_time =", value, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeNotEqualTo(Date value) {
            addCriterion("store_close_time <>", value, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeGreaterThan(Date value) {
            addCriterion("store_close_time >", value, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("store_close_time >=", value, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeLessThan(Date value) {
            addCriterion("store_close_time <", value, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("store_close_time <=", value, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeIn(List<Date> values) {
            addCriterion("store_close_time in", values, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeNotIn(List<Date> values) {
            addCriterion("store_close_time not in", values, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeBetween(Date value1, Date value2) {
            addCriterion("store_close_time between", value1, value2, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("store_close_time not between", value1, value2, "storeCloseTime");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityIsNull() {
            addCriterion("store_identity is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityIsNotNull() {
            addCriterion("store_identity is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityEqualTo(String value) {
            addCriterion("store_identity =", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityNotEqualTo(String value) {
            addCriterion("store_identity <>", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityGreaterThan(String value) {
            addCriterion("store_identity >", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("store_identity >=", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityLessThan(String value) {
            addCriterion("store_identity <", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityLessThanOrEqualTo(String value) {
            addCriterion("store_identity <=", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityLike(String value) {
            addCriterion("store_identity like", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityNotLike(String value) {
            addCriterion("store_identity not like", value, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityIn(List<String> values) {
            addCriterion("store_identity in", values, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityNotIn(List<String> values) {
            addCriterion("store_identity not in", values, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityBetween(String value1, String value2) {
            addCriterion("store_identity between", value1, value2, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreIdentityNotBetween(String value1, String value2) {
            addCriterion("store_identity not between", value1, value2, "storeIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityIsNull() {
            addCriterion("store_manager_identity is null");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityIsNotNull() {
            addCriterion("store_manager_identity is not null");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityEqualTo(String value) {
            addCriterion("store_manager_identity =", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityNotEqualTo(String value) {
            addCriterion("store_manager_identity <>", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityGreaterThan(String value) {
            addCriterion("store_manager_identity >", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("store_manager_identity >=", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityLessThan(String value) {
            addCriterion("store_manager_identity <", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityLessThanOrEqualTo(String value) {
            addCriterion("store_manager_identity <=", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityLike(String value) {
            addCriterion("store_manager_identity like", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityNotLike(String value) {
            addCriterion("store_manager_identity not like", value, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityIn(List<String> values) {
            addCriterion("store_manager_identity in", values, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityNotIn(List<String> values) {
            addCriterion("store_manager_identity not in", values, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityBetween(String value1, String value2) {
            addCriterion("store_manager_identity between", value1, value2, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreManagerIdentityNotBetween(String value1, String value2) {
            addCriterion("store_manager_identity not between", value1, value2, "storeManagerIdentity");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeIsNull() {
            addCriterion("store_longitude is null");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeIsNotNull() {
            addCriterion("store_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeEqualTo(String value) {
            addCriterion("store_longitude =", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotEqualTo(String value) {
            addCriterion("store_longitude <>", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeGreaterThan(String value) {
            addCriterion("store_longitude >", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("store_longitude >=", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeLessThan(String value) {
            addCriterion("store_longitude <", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeLessThanOrEqualTo(String value) {
            addCriterion("store_longitude <=", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeLike(String value) {
            addCriterion("store_longitude like", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotLike(String value) {
            addCriterion("store_longitude not like", value, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeIn(List<String> values) {
            addCriterion("store_longitude in", values, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotIn(List<String> values) {
            addCriterion("store_longitude not in", values, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeBetween(String value1, String value2) {
            addCriterion("store_longitude between", value1, value2, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLongitudeNotBetween(String value1, String value2) {
            addCriterion("store_longitude not between", value1, value2, "storeLongitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeIsNull() {
            addCriterion("store_latitude is null");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeIsNotNull() {
            addCriterion("store_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeEqualTo(String value) {
            addCriterion("store_latitude =", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotEqualTo(String value) {
            addCriterion("store_latitude <>", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeGreaterThan(String value) {
            addCriterion("store_latitude >", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("store_latitude >=", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeLessThan(String value) {
            addCriterion("store_latitude <", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeLessThanOrEqualTo(String value) {
            addCriterion("store_latitude <=", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeLike(String value) {
            addCriterion("store_latitude like", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotLike(String value) {
            addCriterion("store_latitude not like", value, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeIn(List<String> values) {
            addCriterion("store_latitude in", values, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotIn(List<String> values) {
            addCriterion("store_latitude not in", values, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeBetween(String value1, String value2) {
            addCriterion("store_latitude between", value1, value2, "storeLatitude");
            return (Criteria) this;
        }

        public Criteria andStoreLatitudeNotBetween(String value1, String value2) {
            addCriterion("store_latitude not between", value1, value2, "storeLatitude");
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