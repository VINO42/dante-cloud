/*
 * Copyright (c) 2020-2030 ZHENGGENGWEI(码匠君)<herodotus@aliyun.com>
 *
 * Dante Cloud licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Dante Cloud 采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改 Dante Cloud 源码头部的版权声明。
 * 3.请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 https://gitee.com/dromara/dante-cloud
 * 5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/dromara/dante-cloud
 * 6.若您的项目无法满足以上几点，可申请商业授权
 */

package cn.herodotus.dante.module.upms.rest.listener;

import cn.herodotus.dante.module.upms.logic.assistant.event.SysSecurityAttributeChangeEvent;
import cn.herodotus.dante.module.upms.logic.entity.system.SysSecurityAttribute;
import cn.herodotus.dante.module.upms.rest.processor.SecurityAttributeChangedProcessor;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: SysSecurityAttribute变更事件监听 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/8/4 22:18
 */
@Component
public class SysSecurityAttributeChangeListener implements ApplicationListener<SysSecurityAttributeChangeEvent> {

    private static final Logger log = LoggerFactory.getLogger(SysSecurityAttributeChangeListener.class);

    private final SecurityAttributeChangedProcessor securityAttributeChangedProcessor;

    @Autowired
    public SysSecurityAttributeChangeListener(SecurityAttributeChangedProcessor securityAttributeChangedProcessor) {
        this.securityAttributeChangedProcessor = securityAttributeChangedProcessor;
    }

    @Override
    public void onApplicationEvent(SysSecurityAttributeChangeEvent event) {

        log.debug("[Herodotus] |- SysSecurityAttribute Change Listener, response event!");

        SysSecurityAttribute sysSecurityAttribute = event.getData();
        if (ObjectUtils.isNotEmpty(sysSecurityAttribute)) {
            log.debug("[Herodotus] |- Got SysSecurityAttribute, start to process SysSecurityAttribute change.");
            securityAttributeChangedProcessor.distributeChangedSecurityAttribute(sysSecurityAttribute);
        }
    }
}
