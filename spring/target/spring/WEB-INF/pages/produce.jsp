<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>消息队列</title>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        $().ready(function() {
            $("#receiveMsg").click(function () {
                // $.post("receiveMessage", function (result) {
                $.post("getUserInfo", function (result) {
                    alert("结果：" + result)
                }, "json");
            });
        });
    </script>
</head>
<body>
<h2>发送消息成功</h2>

<div>
    <form action="sendMessage" method="post">
        <input type="text" name="message" value="" placeholder="请输入要发送的消息"/>
        <input type="submit" value="发送">
    </form>
    <input type="button" id="receiveMsg" name="receiveMsg" value="获取消息">
</div>
<div class="receive-message-show">
    <span class="msg"></span>
</div>
</body>
</html>
