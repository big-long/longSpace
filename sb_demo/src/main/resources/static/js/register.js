$(function(){
	// 用户名校验
	$("#register").bind("blur", function() {
		var userName = $(this).val();
		if (userName != null && userName != "") {
			$.ajax({
				type : "get",
				url : "/account/checkUserName",
				data : {
					userName : userName
				},
				success : function(result) {
					if (result.status == 200) {
						$("[name=errormsg]").html(result.message);
					} else {
						$("[name=errormsg]").html(result.message);
					}

				},
				error : function() {
					$("[name=errormsg]").html("访问失败");
				}
			});
		} else {
			$("[name=errormsg]").html("请填写有效用户名");
		}
	});

})
