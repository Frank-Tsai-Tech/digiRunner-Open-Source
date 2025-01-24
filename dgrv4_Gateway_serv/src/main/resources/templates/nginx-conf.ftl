server {
    listen ${nginxPort?c}<#if sslEnabled==true> ssl</#if>;
    <#if sslEnabled==true>
    ssl_certificate     ${sslCrtPath};
    ssl_certificate_key ${sslKeyPath};
    error_page 497  301 =307 https://$host:$server_port$request_uri;
    </#if>

    client_max_body_size ${maxBodySize};
    location / {
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass <#if sslEnabled==true>https<#else>http</#if>://localhost:${serverPort?c};

        # WebSocket support
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
}