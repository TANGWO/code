package com.cl.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.cl.entity.House;
import com.cl.entity.HouseExample.Criteria;
import com.cl.entity.HouseExample.Criterion;
import com.cl.entity.HouseExample;
import java.util.List;
import java.util.Map;

public class HouseSqlProvider {

    public String countByExample(HouseExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("house");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(HouseExample example) {
        BEGIN();
        DELETE_FROM("house");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(House record) {
        BEGIN();
        INSERT_INTO("house");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            VALUES("type", "#{type,jdbcType=BIT}");
        }
        
        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=INTEGER}");
        }
        
        if (record.getImages() != null) {
            VALUES("images", "#{images,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            VALUES("area", "#{area,jdbcType=INTEGER}");
        }
        
        if (record.getBeds() != null) {
            VALUES("beds", "#{beds,jdbcType=INTEGER}");
        }
        
        if (record.getBaths() != null) {
            VALUES("baths", "#{baths,jdbcType=INTEGER}");
        }
        
        if (record.getRating() != null) {
            VALUES("rating", "#{rating,jdbcType=DOUBLE}");
        }
        
        if (record.getRemarks() != null) {
            VALUES("remarks", "#{remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getProperties() != null) {
            VALUES("properties", "#{properties,jdbcType=VARCHAR}");
        }
        
        if (record.getFloorPlan() != null) {
            VALUES("floor_plan", "#{floorPlan,jdbcType=VARCHAR}");
        }
        
        if (record.getTags() != null) {
            VALUES("tags", "#{tags,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCityId() != null) {
            VALUES("city_id", "#{cityId,jdbcType=INTEGER}");
        }
        
        if (record.getCommunityId() != null) {
            VALUES("community_id", "#{communityId,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            VALUES("state", "#{state,jdbcType=BIT}");
        }
        
        return SQL();
    }

    public String selectByExample(HouseExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("type");
        SELECT("price");
        SELECT("images");
        SELECT("area");
        SELECT("beds");
        SELECT("baths");
        SELECT("rating");
        SELECT("remarks");
        SELECT("properties");
        SELECT("floor_plan");
        SELECT("tags");
        SELECT("create_time");
        SELECT("city_id");
        SELECT("community_id");
        SELECT("address");
        SELECT("state");
        FROM("house");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        House record = (House) parameter.get("record");
        HouseExample example = (HouseExample) parameter.get("example");
        
        BEGIN();
        UPDATE("house");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            SET("type = #{record.type,jdbcType=BIT}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{record.price,jdbcType=INTEGER}");
        }
        
        if (record.getImages() != null) {
            SET("images = #{record.images,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            SET("area = #{record.area,jdbcType=INTEGER}");
        }
        
        if (record.getBeds() != null) {
            SET("beds = #{record.beds,jdbcType=INTEGER}");
        }
        
        if (record.getBaths() != null) {
            SET("baths = #{record.baths,jdbcType=INTEGER}");
        }
        
        if (record.getRating() != null) {
            SET("rating = #{record.rating,jdbcType=DOUBLE}");
        }
        
        if (record.getRemarks() != null) {
            SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getProperties() != null) {
            SET("properties = #{record.properties,jdbcType=VARCHAR}");
        }
        
        if (record.getFloorPlan() != null) {
            SET("floor_plan = #{record.floorPlan,jdbcType=VARCHAR}");
        }
        
        if (record.getTags() != null) {
            SET("tags = #{record.tags,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCityId() != null) {
            SET("city_id = #{record.cityId,jdbcType=INTEGER}");
        }
        
        if (record.getCommunityId() != null) {
            SET("community_id = #{record.communityId,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            SET("state = #{record.state,jdbcType=BIT}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("house");
        
        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("type = #{record.type,jdbcType=BIT}");
        SET("price = #{record.price,jdbcType=INTEGER}");
        SET("images = #{record.images,jdbcType=VARCHAR}");
        SET("area = #{record.area,jdbcType=INTEGER}");
        SET("beds = #{record.beds,jdbcType=INTEGER}");
        SET("baths = #{record.baths,jdbcType=INTEGER}");
        SET("rating = #{record.rating,jdbcType=DOUBLE}");
        SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        SET("properties = #{record.properties,jdbcType=VARCHAR}");
        SET("floor_plan = #{record.floorPlan,jdbcType=VARCHAR}");
        SET("tags = #{record.tags,jdbcType=VARCHAR}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("city_id = #{record.cityId,jdbcType=INTEGER}");
        SET("community_id = #{record.communityId,jdbcType=INTEGER}");
        SET("address = #{record.address,jdbcType=VARCHAR}");
        SET("state = #{record.state,jdbcType=BIT}");
        
        HouseExample example = (HouseExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(House record) {
        BEGIN();
        UPDATE("house");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            SET("type = #{type,jdbcType=BIT}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=INTEGER}");
        }
        
        if (record.getImages() != null) {
            SET("images = #{images,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            SET("area = #{area,jdbcType=INTEGER}");
        }
        
        if (record.getBeds() != null) {
            SET("beds = #{beds,jdbcType=INTEGER}");
        }
        
        if (record.getBaths() != null) {
            SET("baths = #{baths,jdbcType=INTEGER}");
        }
        
        if (record.getRating() != null) {
            SET("rating = #{rating,jdbcType=DOUBLE}");
        }
        
        if (record.getRemarks() != null) {
            SET("remarks = #{remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getProperties() != null) {
            SET("properties = #{properties,jdbcType=VARCHAR}");
        }
        
        if (record.getFloorPlan() != null) {
            SET("floor_plan = #{floorPlan,jdbcType=VARCHAR}");
        }
        
        if (record.getTags() != null) {
            SET("tags = #{tags,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCityId() != null) {
            SET("city_id = #{cityId,jdbcType=INTEGER}");
        }
        
        if (record.getCommunityId() != null) {
            SET("community_id = #{communityId,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            SET("state = #{state,jdbcType=BIT}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    protected void applyWhere(HouseExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}