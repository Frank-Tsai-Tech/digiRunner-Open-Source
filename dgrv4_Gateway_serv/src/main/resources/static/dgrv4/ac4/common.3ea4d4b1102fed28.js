(self.webpackChunksrcAngular=self.webpackChunksrcAngular||[]).push([[8592],{58838:(P,y,a)=>{"use strict";a.d(y,{n:()=>p});var _=a(27896),n=a(92340),c=a(57553),v=a(95244),o=a(87587),i=a(22313),t=a(45682),r=a(40704),e=a(69808);function u(m,A){if(1&m&&o._UZ(0,"iframe",1),2&m){const s=o.oxw();o.Q6J("src",s.url,o.uOi)}}const R=a(35117);let p=(()=>{class m{constructor(s,l,h,g){this.sanitizer=s,this.util=l,this.tool=h,this.alertService=g,this.reportID="",this.url="",this.timeRange=c.tn.Today,this.showStatus=!1,this.msgListenterAdd=!1,this.processing=!1,this.messageHandler=this.createMessageHandler()}ngOnInit(){this.showStatus=!1,this.url=this.sanitizer.bypassSecurityTrustResourceUrl(""),this.search(),window.addEventListener("message",this.messageHandler)}ngOnDestroy(){window.removeEventListener("message",this.messageHandler)}createMessageHandler(){return s=>{var l,h,g,f;"frame"==(null===(l=s.data)||void 0===l?void 0:l.from)&&"token_expired"==(null===(h=s.data)||void 0===h?void 0:h.type)?this.processing||(this.processing=!0,this.util.queryReportUrls({reportID:this.reportID.toUpperCase(),timeRange:"T"}).subscribe(E=>{var D;this.processing=!1,this.tool.checkDpSuccess(E.ResHeader)&&(null===(D=document.getElementById("_frame").contentWindow)||void 0===D||D.postMessage({from:"dgr",type:"redo"},"*"))})):"frame"==(null===(g=s.data)||void 0===g?void 0:g.from)&&"reset_idle_time"==(null===(f=s.data)||void 0===f?void 0:f.type)&&this.tool.setExpiredTime()}}search(s=!1){let l=this.generateCAPI();this.timeRange&&this.util.queryReportUrls({reportID:this.reportID.toUpperCase(),timeRange:"T"}).subscribe(h=>{if(this.tool.checkDpSuccess(h.ResHeader)){if(this.showStatus=!0,s)return;let g="";g="localhost"==location.hostname||"127.0.0.1"==location.hostname?n.N.apiUrl:`${location.protocol}//${location.hostname}:${location.port}`;let f="";switch(h.RespBody.reportType){case"SYS_RPT":f=`${g}${h.RespBody.rpContentPath}/login?cuuid=${l.uuid}&capi_key=${l.capi}&reportURL=${h.RespBody.reportUrl}&embed=true`,this.url=this.sanitizer.bypassSecurityTrustResourceUrl(f);break;case"IFRAME":f=n.N.production?`${location.protocol}//${location.hostname}:${location.port}${h.RespBody.reportUrl}`:`${n.N.cusHostName}${h.RespBody.reportUrl}`,this.url=this.sanitizer.bypassSecurityTrustResourceUrl(f);break;case"LINK":f=`${h.RespBody.reportUrl}`,window.open(f,"_blank");break;default:this.alertService.ok("reportType not found","")}}})}generate_uuid(){var s=Date.now();return"undefined"!=typeof performance&&"function"==typeof performance.now&&(s+=performance.now()),"xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,function(l){var h=(s+16*Math.random())%16|0;return s=Math.floor(s/16),("x"===l?h:3&h|8).toString(16)})}generateCAPI(){let s=this.generate_uuid().toUpperCase(),l=this.encodeUUID(s);return"Error: Digest method not supported"===l&&(s="67C42D24-BCFD-40B1-99E9-C4CE4D9ADBCD",l="tflvB2FW3nhUIvF3PMHQxw"),{uuid:s,capi:l}}encodeUUID(s){if("string"!=typeof s||0===s.length)return"";const h=Uint8Array.of(128),g=new TextEncoder;try{let f=s.toLocaleUpperCase()+"TPIdigiRunner",E=g.encode(f),D=this.concatenate(Uint8Array,E,h),B=v("sha256").update(D).digest(),L=B.subarray(B.length/2,B.length);return R(L)}catch(f){return console.log("e",f),f}}concatenate(s,...l){let h=0;for(const E of l)h+=E.length;const g=new s(h);let f=0;for(const E of l)g.set(E,f),f+=E.length;return g}}return m.\u0275fac=function(s){return new(s||m)(o.Y36(i.H7),o.Y36(_.f),o.Y36(t.g),o.Y36(r.c))},m.\u0275cmp=o.Xpm({type:m,selectors:[["app-frame"]],inputs:{reportID:"reportID"},features:[o._Bn([_.f])],decls:1,vars:1,consts:[["id","_frame","title","","width","100%","height","100%","frameborder","0",3,"src",4,"ngIf"],["id","_frame","title","","width","100%","height","100%","frameborder","0",3,"src"]],template:function(s,l){1&s&&o.YNc(0,u,1,1,"iframe",0),2&s&&o.Q6J("ngIf",l.showStatus)},directives:[e.O5],styles:[""]}),m})()},58778:(P,y,a)=>{"use strict";a.d(y,{L:()=>v});var _=a(71764),c=a(87587);let v=(()=>{class d{transform(i,t){if(!i||20!=i.length)return;let e=i.substr(0,4),u=i.substr(4,2),R=i.substr(6,2),p=i.substr(9,2),m=i.substr(11,2),A=i.substr(13,2),s=i.slice(15);s=s.slice(0,3)+":"+s.slice(3);let l=_(`${e}-${u}-${R} ${p}:${m}:${A}`).toDate();return"time_ignore"===t?_(l).format("YYYY-MM-DD"):_(l).format("YYYY-MM-DD HH:mm:ss")}}return d.\u0275fac=function(i){return new(i||d)},d.\u0275pipe=c.Yjl({name:"datetime_format",type:d,pure:!0}),d})()},22751:(P,y,a)=>{"use strict";a.d(y,{Q:()=>n});var _=a(87587);let n=(()=>{class c{transform(d,o){return d?d.length>o?d.substring(0,o)+"...":d:""}}return c.\u0275fac=function(d){return new(d||c)},c.\u0275pipe=_.Yjl({name:"string_length",type:c,pure:!0}),c})()},78215:(P,y,a)=>{"use strict";a.d(y,{h:()=>d});var _=a(92340),n=a(57553),c=a(87587),v=a(96614);let d=(()=>{class o{constructor(t){this.api=t,this.api.baseUrl=_.N.dpPath}get basePath(){return _.N.isv4?"dgrv4/11":"tsmpdpaa/11"}signReq(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.signReq),ReqBody:t};return this.api.npPost(`${this.basePath}/DPB0071`,r)}queryApiDpStatusLikeList_ignore1298(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryApiDpStatusLikeList),ReqBody:t};return this.api.excuteNpPost_ignore1298(`${this.basePath}/DPB0072`,r)}queryApiDpStatusLikeList(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryApiDpStatusLikeList),ReqBody:t};return this.api.npPost(`${this.basePath}/DPB0072`,r)}setApiPublicFlag(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.setApiPublicFlag),ReqBody:t};return this.api.npPost(`${this.basePath}/DPB0073`,r)}}return o.\u0275fac=function(t){return new(t||o)(c.LFG(v.K))},o.\u0275prov=c.Yz7({token:o,factory:o.\u0275fac,providedIn:"root"}),o})()},95536:(P,y,a)=>{"use strict";a.d(y,{B:()=>d});var _=a(92340),n=a(57553),c=a(87587),v=a(96614);let d=(()=>{class o{constructor(t){this.api=t}get npBasePath(){return _.N.isv4?"dgrv4/11":"tsmpdpaa/11"}queryTSecurityLV(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryTSecurityLV),ReqBody:t};return this.api.npPost(`${this.npBasePath}/AA1102`,r)}addTGroupAuthority_before(){let t={ReqHeader:this.api.getReqHeader(n.Nx.addTGroupAuthority),ReqBody:{}};return this.api.npPost(`${this.npBasePath}/AA1106?before`,t)}addTGroupAuthority(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.addTGroupAuthority),ReqBody:t};return this.api.npPost(`${this.npBasePath}/AA1106`,r)}updateTGroupAuthority_before(){let t={ReqHeader:this.api.getReqHeader(n.Nx.updateTGroupAuthority),ReqBody:{}};return this.api.npPost(`${this.npBasePath}/AA1108?before`,t)}updateTGroupAuthority(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.updateTGroupAuthority),ReqBody:t};return this.api.npPost(`${this.npBasePath}/AA1108`,r)}queryTGroupAuthorityDetail(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryTGroupAuthorityDetail),ReqBody:t};return this.api.npPost(`${this.npBasePath}/AA1109`,r)}deleteTGroupAuthority(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.deleteTGroupAuthority),ReqBody:t};return this.api.npPost(`${this.npBasePath}/AA1110`,r)}queryScopeAuthorities_ignore1298(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryScopeAuthorities),ReqBody:t};return this.api.excuteNpPost_ignore1298(`${this.npBasePath}/AA1115`,r)}queryScopeAuthorities(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryScopeAuthorities),ReqBody:t};return this.api.npPost(`${this.npBasePath}/AA1115`,r)}}return o.\u0275fac=function(t){return new(t||o)(c.LFG(v.K))},o.\u0275prov=c.Yz7({token:o,factory:o.\u0275fac,providedIn:"root"}),o})()},75809:(P,y,a)=>{"use strict";a.d(y,{A:()=>d});var _=a(92340),n=a(57553),c=a(87587),v=a(96614);let d=(()=>{class o{constructor(t){this.api=t,this.api.baseUrl=_.N.dpPath}get basePath(){return _.N.isv4?"dgrv4/11":"tsmpdpaa/11"}queryApiLov_ignore1298(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryApiLov),ReqBody:t};return this.api.excuteNpPost_ignore1298(`${this.basePath}/DPB0075`,r)}queryApiLov(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryApiLov),ReqBody:t};return this.api.npPost(`${this.basePath}/DPB0075`,r)}queryThemeLov_ignore1298(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryThemeLov),ReqBody:t};return this.api.excuteNpPost_ignore1298(`${this.basePath}/DPB0076`,r)}queryThemeLov(t){let r={ReqHeader:this.api.getReqHeader(n.Nx.queryThemeLov),ReqBody:t};return this.api.npPost(`${this.basePath}/DPB0076`,r)}}return o.\u0275fac=function(t){return new(t||o)(c.LFG(v.K))},o.\u0275prov=c.Yz7({token:o,factory:o.\u0275fac,providedIn:"root"}),o})()},79581:(P,y,a)=>{"use strict";var n=a(8708);function c(r,e){return void 0===e&&(e="utf8"),Buffer.isBuffer(r)?o(r.toString("base64")):o(Buffer.from(r,e).toString("base64"))}function d(r){return r=r.toString(),n.default(r).replace(/\-/g,"+").replace(/_/g,"/")}function o(r){return r.replace(/=/g,"").replace(/\+/g,"-").replace(/\//g,"_")}var t=c;t.encode=c,t.decode=function v(r,e){return void 0===e&&(e="utf8"),Buffer.from(d(r),"base64").toString(e)},t.toBase64=d,t.fromBase64=o,t.toBuffer=function i(r){return Buffer.from(d(r),"base64")},y.default=t},8708:(P,y)=>{"use strict";Object.defineProperty(y,"__esModule",{value:!0}),y.default=function a(_){var c=_.length,v=c%4;if(!v)return _;var d=c,o=4-v,t=Buffer.alloc(c+o);for(t.write(_);o--;)t.write("=",d++);return t.toString()}},35117:(P,y,a)=>{P.exports=a(79581).default,P.exports.default=P.exports},94327:function(P,y){var n;void 0!==(n=function(){"use strict";function v(e,u,R){var p=new XMLHttpRequest;p.open("GET",e),p.responseType="blob",p.onload=function(){r(p.response,u,R)},p.onerror=function(){console.error("could not download file")},p.send()}function d(e){var u=new XMLHttpRequest;u.open("HEAD",e,!1);try{u.send()}catch(R){}return 200<=u.status&&299>=u.status}function o(e){try{e.dispatchEvent(new MouseEvent("click"))}catch(R){var u=document.createEvent("MouseEvents");u.initMouseEvent("click",!0,!0,window,0,0,0,80,20,!1,!1,!1,!1,0,null),e.dispatchEvent(u)}}var i="object"==typeof window&&window.window===window?window:"object"==typeof self&&self.self===self?self:"object"==typeof global&&global.global===global?global:void 0,t=i.navigator&&/Macintosh/.test(navigator.userAgent)&&/AppleWebKit/.test(navigator.userAgent)&&!/Safari/.test(navigator.userAgent),r=i.saveAs||("object"!=typeof window||window!==i?function(){}:"download"in HTMLAnchorElement.prototype&&!t?function(e,u,R){var p=i.URL||i.webkitURL,m=document.createElement("a");m.download=u=u||e.name||"download",m.rel="noopener","string"==typeof e?(m.href=e,m.origin===location.origin?o(m):d(m.href)?v(e,u,R):o(m,m.target="_blank")):(m.href=p.createObjectURL(e),setTimeout(function(){p.revokeObjectURL(m.href)},4e4),setTimeout(function(){o(m)},0))}:"msSaveOrOpenBlob"in navigator?function(e,u,R){if(u=u||e.name||"download","string"!=typeof e)navigator.msSaveOrOpenBlob(function c(e,u){return void 0===u?u={autoBom:!1}:"object"!=typeof u&&(console.warn("Deprecated: Expected third argument to be a object"),u={autoBom:!u}),u.autoBom&&/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(e.type)?new Blob(["\ufeff",e],{type:e.type}):e}(e,R),u);else if(d(e))v(e,u,R);else{var p=document.createElement("a");p.href=e,p.target="_blank",setTimeout(function(){o(p)})}}:function(e,u,R,p){if((p=p||open("","_blank"))&&(p.document.title=p.document.body.innerText="downloading..."),"string"==typeof e)return v(e,u,R);var m="application/octet-stream"===e.type,A=/constructor/i.test(i.HTMLElement)||i.safari,s=/CriOS\/[\d]+/.test(navigator.userAgent);if((s||m&&A||t)&&"undefined"!=typeof FileReader){var l=new FileReader;l.onloadend=function(){var f=l.result;f=s?f:f.replace(/^data:[^;]*;/,"data:attachment/file;"),p?p.location.href=f:location=f,p=null},l.readAsDataURL(e)}else{var h=i.URL||i.webkitURL,g=h.createObjectURL(e);p?p.location=g:location.href=g,p=null,setTimeout(function(){h.revokeObjectURL(g)},4e4)}});i.saveAs=r.saveAs=r,P.exports=r}.apply(y,[]))&&(P.exports=n)}}]);