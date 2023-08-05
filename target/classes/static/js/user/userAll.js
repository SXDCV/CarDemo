var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	// 分页
	getAll();
	//新增员工
	getAdd();
	
});


/**********新增员工信息***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增员工信息',
			area:['40%','85%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"User.do?method=toadd",
			end:function(){
				$("#tab").bootstrapTable('refresh');
			}
		});
	});
	
}



/** *******分页查询********** */
function getAll() {
	$('#tab').bootstrapTable(
					{
						url :'User.do?method=getByPages', // 请求后台的URL（*）
						method : 'get', // 请求方式（*）
						dataType : 'json',
						toolbar : '#toolbar', // 工具按钮用哪个容器
						striped : true, // 是否显示行间隔色
						pagination : true, // 是否显示分页（*）

						queryParams : function(params) {
							var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
								pages : params.limit, // 页面大小(每一页要显示多少条)
								begin : params.offset
							// 从数据库第几条记录开始
							};
							return temp;
						},// 传递参数（*）

						sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
						pageNumber : 1, // 初始化加载第一页，默认第一页
						pageSize : 5, // 每页的记录行数（*）
						pageList : [ 5, 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
						showColumns : true, // 是否显示所有的列
						showRefresh : true, // 是否显示刷新按钮
						minimumCountColumns : 1, // 最少允许的列数
						clickToSelect : true, // 是否启用点击选中行
						uniqueId : "uid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								{
									field : 'uid',
									align : 'center',
									title : '编号'
								},
								{
									field : 'uname',
									align : 'center',
									title : '员工名称',
									formatter : function(value, row, index) {
										var e = "<span  class='uname'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'pwd',
									align : 'center',
									title : '员工密码',
									formatter : function(value, row, index) {
										var e = "<span  class='upwd'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'username',
									align : 'center',
									title : '真实姓名',
									formatter : function(value, row, index) {
										var e = "<span  class='username'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'sex',
									align : 'center',
									title : '性别',
									formatter : function(value, row, index) {
										var e = "<span  class='sex'>" + value
												+ "</span>";
										return e;
									}
								},
								{
									field : 'utel',
									align : 'center',
									title : '电话',
									formatter : function(value, row, index) {
										var e = "<span  class='utel'>" + value
												+ "</span>";
										return e;
									}
								},
								{
									title : '操作',
									field : 'uid',
									align : 'center',
									formatter : function(value, row, index) {

									var e = '<a href="#" class="btn btn-info btn-xs" onclick="edit(\'' + value
										+ '\')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;编辑</a> ';
									return e;
									}
								} ]
					});
}
//修改
function edit(uid){
	layer.open({
		type:2,
		title:'修改员工信息',
		area:['40%','80%'],
		shadeClose:true,
		skin:'layui-layer-lan',
		maxmin:true,
		content:"User.do?method=UserOne&uid="+uid,
		end:function(){
			$("#tab").bootstrapTable('refresh');
		}
	});
}

