/*
 * Copyright 2014-2019 the original author or authors.
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
 * update by somnus丶y   解决nginx代理host访问不了问题
 */

package de.codecentric.boot.admin.server.ui.web;

import cn.blocks.commonutils.utils.EnvironmentUtils;
import de.codecentric.boot.admin.server.ui.extensions.UiExtension;
import de.codecentric.boot.admin.server.web.AdminController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nullable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@AdminController
public class UiController {
    private final String publicUrl;
    private final List<UiExtension> cssExtensions;
    private final List<UiExtension> jsExtensions;
    private final Map<String, Object> uiSettings;

    public UiController(String publicUrl, String title, String brand, String favicon,
                        String faviconDanger,
                        List<UiExtension> uiExtensions,
                        boolean notificationFilterEnabled) {
        this.publicUrl = publicUrl;
        this.uiSettings = new HashMap<>();
        this.uiSettings.put("title", title);
        this.uiSettings.put("brand", brand);
        this.uiSettings.put("favicon", favicon);
        this.uiSettings.put("faviconDanger", faviconDanger);
        this.uiSettings.put("notificationFilterEnabled", notificationFilterEnabled);
        this.cssExtensions = uiExtensions.stream()
                .filter(e -> e.getResourcePath().endsWith(".css"))
                .collect(Collectors.toList());
        this.jsExtensions = uiExtensions.stream()
                .filter(e -> e.getResourcePath().endsWith(".js"))
                .collect(Collectors.toList());
    }

    @ModelAttribute(value = "baseUrl", binding = false)
    public String getBaseUrl(UriComponentsBuilder uriBuilder) {
        UriComponents publicComponents = UriComponentsBuilder.fromUriString(publicUrl).build();
        if (publicComponents.getScheme() != null) {
            uriBuilder.scheme(publicComponents.getScheme());
        }
        if (publicComponents.getHost() != null) {
            uriBuilder.host(publicComponents.getHost());
        }
        if (publicComponents.getPort() != -1) {
            uriBuilder.port(publicComponents.getPort());
        }
        if (publicComponents.getPath() != null) {
            uriBuilder.path(publicComponents.getPath());
        }
        String preUrl = uriBuilder.path("/").toUriString();
        /*RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String preUrl = request.getRequestURL().toString();*/
        String strEnv = EnvironmentUtils.getStrEnv("admin.preUri");
        if(StringUtils.isEmpty(strEnv)){
            return preUrl;
        }
        return strEnv;
    }

    @ModelAttribute(value = "uiSettings", binding = false)
    public Map<String, Object> getUiSettings() {
        return uiSettings;
    }

    @ModelAttribute(value = "cssExtensions", binding = false)
    public List<UiExtension> getCssExtensions() {
        return cssExtensions;
    }

    @ModelAttribute(value = "jsExtensions", binding = false)
    public List<UiExtension> getJsExtensions() {
        return jsExtensions;
    }

    @ModelAttribute(value = "user", binding = false)
    public Map<String, Object> getUser(@Nullable Principal principal) {
        if (principal != null) {
            return singletonMap("name", principal.getName());
        }
        return emptyMap();
    }

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return "index";
    }

    @GetMapping(path = "/sba-settings.js", produces = "text/javascript")
    public String sbaSettings() {
        return "sba-settings.js";
    }

    @GetMapping(path = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public String login() {
        return "login";
    }
}
