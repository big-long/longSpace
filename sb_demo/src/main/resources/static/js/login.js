$(function(){
	function getNumber() {
		$("img").attr("src", "/account/captcha.jpg?ran=" + Math.random());
	};
	// 用户名校验
	$("#userName").bind("blur", function() {
		var userName = $("#userName").val();
		if (userName != null && userName != "") {
			$("#message").html("");
			/*$.ajax({
				type : "get",
				url : "/account/userDetail",
				data : {
					userName : userName
				},
				success : function(data) {
					var result = eval(data);
					if (result.status == 200) {
						$("#message").html("");
					} else {
						$("#message").html("用户名不存在");
					}

				},
				error : function() {
					$("#message").html("访问失败");
				}
			});*/
		} else {
			$("#message").html("请填写用户名");
		}
	});

	// 用户登陆
	$("#loginButton").bind(
			"click",
			function() {
				if ($("[name=userName]").val() != ""
						&& $("[name=password]").val() != "") {
					var userName = $("#userName").val();
					var password = $("input[type=password]").val();
					var captcha = $("[name=captcha]").val();
					var rememberMe = $("[name=rememberMe]").prop("checked");
					
					if (captcha != "") {
						
						$.ajax({
							type : "post",
							url : "/account/doLogin",
							data : {
								userName : userName,
								password : password,
								captcha : captcha,
								rememberMe:rememberMe
							},
							success : function(data) {
								var result = eval(data);

								if (result.status == 200) {
									location.href = "/account/dashboard";
									// location.href = "/index";
								} else {
									getNumber() ;
									$("#msg").html(result.message)
								}
							},
							error : function(data) {
								getNumber(); 
								var result = eval(data);
								$("#msg").html(result.message)
							}
						})
					}else{
						$("#msg").html("验证码不能为空");
					}
				}
			});
})
function getNumber() {
	$("img").attr("src", "/account/captcha.jpg?ran=" + Math.random());
}