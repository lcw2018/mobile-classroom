package com.wys.mcr.common.web;

import com.google.common.base.Strings;
import com.wys.mcr.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lcw
 */
@Order(1)
@Slf4j
@WebFilter(filterName = "validateFilter", urlPatterns = "/*")
public class HttpServletRequestReplacedFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) {
    }

    /**
     * 过滤器
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            if (!isMultipartContent(req)) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper(req);
            }
        }
        chain.doFilter(requestWrapper == null ? request : requestWrapper, response);
    }

    /**
     * 判断是否为MultipartContent
     */
    private static boolean isMultipartContent(HttpServletRequest request) {
        /**
         * 获取contentType
         */
        String contentType = request.getContentType();
        /**
         * 判断请求类型
         */
        return StringUtils.POST.equals(request.getMethod().toLowerCase())
                && !Strings.isNullOrEmpty(contentType)
                && (contentType.toLowerCase().startsWith("multipart/"));
    }
}
