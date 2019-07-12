<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<html>
<body>
<h2>消息队列</h2>

<div>
    <form action="sendMessage" method="post">
        <input type="text" name="message" value="" placeholder="请输入要发送的消息"/>
        <input type="submit" value="发送">
    </form>
    <form action="getUserInfo" method="post">
        输入id:<input name="id" type="text" value="">
        <input type="submit" value="获取数据">
    </form>
</div>
</body>
</html>
