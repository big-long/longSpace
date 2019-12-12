$(function() {
	//批量删除
	$("#deleteChecked").bind("click",function(){
		if (confirm("您确定要删除吗?")) {
			var userIdArr=new Array();
			$("[name=checkbox]").each(function(){
				if(this.checked){
					var userId=$(this).parent().next().text();
					userIdArr.push(userId);
					
				}
			});
			$.ajax({
				url:"/account/deleteManyUsers",
				type:"get",
				data:{
					userIdArr:userIdArr
				},
				traditional : true,
				success:function(result){
					console.debug(result.status)
					if(result.status==200){
						$("[name=checkbox]").each(function(){
							if($(this).prop('checked')){
								$(this).parents("tr").remove()
							}
						})
					}
				},
				error:function(result){
					console.debug("wwww")
				}
			})
		}
	})
	// 多选
	$("[name=checkAll]").bind("click", function() {
		var flag = $(this).prop('checked');
		if(flag){
			$("[name=checkbox]").each(function(){
				this.checked=true;
			});
		}else{
			$("[name=checkbox]").each(function(){
				this.checked=false;
			});
		}
	});
	// 加载资源
	$("#loadResource").bind("click", function() {
		$.ajax({
			url : "/account/loadResource",
			success : function(data) {
				var result = eval(data);
				if (result.status == 200) {
					location.href = "/account/resources/1/10";
				} else {
					alert(result.message);
				}
			}
		})
	});
	// 用户注册
	$("#registerButton").bind("click", function() {
		var userName = $("[name=userName]").val();
		var password = $("[name=password]").val();
		var message = $("[name=errormsg]").html();
		if (message != "") {
			$.ajax({
				type : "post",
				url : "/account/doRegister",
				data : {
					userName : userName,
					password : password
				},
				success : function(result) {
					if (result.status == 200) {
						location.href = "/account/login"
					} else {
						$("lable").html(result.message)
					}
				},
				error : function() {
					alert("出错了")
				}

			})

		}

	});

	// $("li").click(function(){
	// console.debug("11");
	// $("li").removeClass("active");
	// $(this).addClass("active");
	// })
	// 分配角色页面
	$("a[name=userEdit]").bind("click", function() {
		$("#userEdit").css("display", "");
		$("#userList").css("display", "none");
		var userId = $(this).parent().prev().prev().prev().html();
		var userName = $(this).parent().prev().prev().html();
		$("#userId").val(userId);
		$("#userName").val(userName);
		$.ajax({
			url : "/account/user/role/" + userId,
			success : function(data) {
				var roles_db = eval(data);
				var roles = $("[name=roleCheckbox]");
				for (var i = 0; i < roles.length; i++) {
					var role = roles[i];
					for (var j = 0; j < roles_db.length; j++) {
						if (roles[i].value == roles_db[j].roleId + "") {
							roles[i].checked = true;
						}
					}

				}

			}
		})
	});
	/*
	 * //修改用户信息 $("tr[name=userTr]").bind("dblclick",function(){ var
	 * userId=$(this).find("tr[name=userId]").val(); var
	 * userId=$(this).find("tr[name=userName]").val(); })
	 */
	// 分配角色
	$("#userSubmit").bind("click", function() {
		var userId = $("#userId").val();
		var arr = new Array();
		var roles = $("[name=roleCheckbox]");
		for (var i = 0; i < roles.length; i++) {
			if (roles[i].checked) {
				arr.push(roles[i].value);
			}
		}
		$.ajax({
			url : "/account/editUser",
			type : "post",
			data : {
				userId : userId,
				arr : arr
			},
			traditional : true,
			success : function(result) {
				/* var result =eval(data); */
				if (result.status == 200) {
					location.href = "/account/users/1/3"
				} else {
					alert(result.message);
				}
			},
			error : function() {
				console.debug("失败")
			}

		})
	});
	// 删除用户
	$("[name=userDelete]").bind("click", function() {
		var userId = $(this).parents("tr").find("td[name=userId]").text();
		if (confirm("您确定要删除吗?")) {
			$.ajax({
				url : "/account/deleteUser/" + userId,
				success : function(data) {
					var result = eval(data);
					if (result.status == 200) {
						location.href = "/account/users/1/2";
					} else {
						alert(result.message);
					}
				}
			})
		}
	});
	// 新增角色页面
	$("#addRole").bind("click", function() {
		$("#roleEdit").css("display", "");
		$("#roleList").css("display", "none");
		$("#roleEdit").find("h2").text("新增角色");
		$("#roleId").val(0);
	});
	// 修改角色页面
	$("a[name=editRole]").bind("click", function() {
		$("#roleEdit").css("display", "");
		$("#roleList").css("display", "none");
		var roleId = $(this).parent().prev().prev().prev().html();
		var roleName = $(this).parent().prev().prev().html();
		var name = $(this).parent().prev().html();
		$("#roleId").val(roleId);
		$("#name").val(name);
		$("#roleName").val(roleName);

	});
	// 修改角色
	$("button[name=rolesubmit]").bind("click", function() {
		var roleId = $("#roleId").val();
		var name = $("#name").val();
		var roleName = $("#roleName").val();
		$.ajax({
			url : "/account/editRole",
			data : {
				roleId : roleId,
				roleName : roleName,
				name : name
			},
			success : function(data) {
				var result = eval(data);
				if (result.status == 200) {
					location.href = "/account/roles/1/2";
				} else {
					alert(result.message);
				}
			},
			error : function() {
				console.debug("失败了");
			}

		})
	});
	// 删除角色
	$("a[name=deleteRole]").bind("click", function() {
		var roleId = $(this).parents("tr").find("td[name=roleId]").text();
		if (confirm("您确定要删除吗?")) {
			$.ajax({
				url : "/account/deleteRole/" + roleId,
				success : function(data) {
					var result = eval(data);
					if (result.status == 200) {
						location.href = "/account/roles/1/2";
					} else {
						alert(result.message);
					}
				}
			})
		}
	});
	// 新增资源页面
	$("#addResource").bind("click", function() {
		$("#resourceEdit").css("display", "");
		$("#resourceList").css("display", "none");
		$("#resourceEdit").find("h2").text("新增资源");
		$("#resourceId").val(0);
	});
	// 编辑资源页面
	$("[name=editResource]").bind(
			"click",
			function() {
				$("#resourceEdit").css("display", "");
				$("#resourceList").css("display", "none");
				var resourceId = $(this).parents("tr")
						.find("[name=resourceId]").text();
				var resourceName = $(this).parents("tr").find(
						"[name=resourceName]").text();
				var resourceUrl = $(this).parents("tr").find(
						"[name=resourceUrl]").text();
				var permission = $(this).parents("tr")
						.find("[name=permission]").text();
				$("#resourceId").val(resourceId);
				$("#resourceName").val(resourceName);
				$("#resourceUrl").val(resourceUrl);
				$("#permission").val(permission);
				$.ajax({
					url : "/account/roles/resource/" + resourceId,
					success : function(data) {
						var roles_db = eval(data);
						var roles = $("[name=roleCheckbox]");
						for (var i = 0; i < roles.length; i++) {
							var role = roles[i];
							for (var j = 0; j < roles_db.length; j++) {
								if (roles[i].value == roles_db[j].roleId + "") {
									roles[i].checked = true;
								}
							}
						}
					}
				})
			});
	// 修改资源
	$("#resourceSubmit").bind("click", function() {
		var resourceId = $("#resourceId").val();
		var resourceName = $("#resourceName").val();
		var resourceUrl = $("#resourceUrl").val();
		var permission = $("#permission").val();
		var arr = new Array();
		var roles = $("[name=roleCheckbox]");
		for (var i = 0; i < roles.length; i++) {
			if (roles[i].checked) {
				arr.push(roles[i].value);
			}
		}
		$.ajax({
			url : "/account/editResource",
			data : {
				resourceId : resourceId,
				resourceName : resourceName,
				resourceUrl : resourceUrl,
				permission : permission,
				arr : arr
			},
			traditional : true,
			success : function(data) {
				var result = eval(data);
				if (result.status == 200) {
					location.href = "/account/resources/1/2";
				} else {
					alert(result.message);
				}
			},
			error : function() {
				console.debug("失败了!");
			}

		})

	});
	// 删除资源
	$("a[name=resourceDelete]").bind(
			"click",
			function() {
				var resourceId = $(this).parents("tr").find(
						"td[name=resourceId]").text();
				if (confirm("您确定要删除吗?")) {
					$.ajax({
						url : "/account/deleteResource/" + resourceId,
						success : function(data) {
							var result = eval(data);
							if (result.status == 200) {
								location.href = "/account/resources/1/2";
							} else {
								alert(result.message);
							}
						}
					})
				}
			});
});
