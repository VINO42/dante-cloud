/*
 * Copyright (c) 2019-2021 Gengwei Zheng (herodotus@aliyun.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project Name: eurynome-cloud
 * Module Name: eurynome-cloud-bpmn-rest
 * File Name: ActIdGroup.java
 * Author: gengwei.zheng
 * Date: 2021/07/20 19:12:20
 */

package cn.herodotus.eurynome.bpmn.rest.entity;

import cn.herodotus.eurynome.bpmn.rest.domain.base.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <p>Description: 工作流组表 </p>
 * <p>
 * 集成进入微服务中，目前考虑该表与SysDepartment对应。使用Debezium进行数据同步。
 *
 * @author : gengwei.zheng
 * @date : 2021/7/20 12:55
 */
@ApiModel(description = "Camunda 组")
@Entity
@Table(name = "act_id_group", indexes = {@Index(name = "act_id_group_id_idx", columnList = "id_")})
public class ActIdGroup extends BaseEntity {

    @JSONField(name = "department_id")
    @JsonProperty("department_id")
    @ApiModelProperty(value = "部门ID")
    @Id
    @GeneratedValue(generator = "act-group-uuid")
    @GenericGenerator(name = "act-group-uuid", strategy = "cn.herodotus.eurynome.bpmn.rest.generator.ActIdGroupUUIDGenerator")
    @Column(name = "id_", length = 64)
    private String id;

    @ApiModelProperty(value = "修订")
    @JSONField(name = "revision")
    @JsonProperty("revision")
    @Column(name = "rev_")
    private Integer revision;

    @ApiModelProperty(value = "名称")
    @JSONField(name = "department_name")
    @JsonProperty("department_name")
    @Column(name = "name_")
    private String name;

    @ApiModelProperty(value = "类型")
    @JSONField(name = "organization_id")
    @JsonProperty("organization_id")
    @Column(name = "type_")
    private String type;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("revision", revision)
                .add("name", name)
                .add("type", type)
                .toString();
    }
}
