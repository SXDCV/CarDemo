var zu=/^[\u0391-\uFFE5A-Za-z]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	// 分页
	getAll();
	//新增汽车系列
	getAdd();
	// 行内修改汽车系列
	getChangeAName();
	// 行内修改汽车系列
	getChangeXName();
	
	
	
	
});

//把字符串变变成json格式
function strToJson(str){
	var json = eval('(' + str + ')');
	return json;
} 

function getChangeXName() {
	$("body").on('click', '#tab .xname', function() {
		var flag;
		var oldDname = $(this).parent().parent().find("td:eq(2)").text();
		// 第一步：得到你要修改对象的主键值
		var id = $(this).parent().parent().find("td:eq(0)").text();

		// 第二步：弹出一个修改层
		$(this).editable({
			type : "text", // 编辑框的类型。支持text|textarea|select|date|checklist等
			title : "修改名称", // 编辑框的标题
			disabled : false, // 是否禁用编辑
			emptytext : "空文本", // 空值的默认文本
			//source: strToJson($("#carxl").val()), //动态形成下拉框
			mode : "popup", // 编辑框的模式：支持popup和inline两种模式，默认是popup
			validate : function(value) {
				if (value.length==0) {
					return '请填写名称'
				} else if(oldDname!=value) {
					$.ajax({
						url:"carxl.do?method=getCkName",
						data:{"xname":value},
						dataType:'json',
						type:'post',
						async:false,
						success:function(mydata){
							if (mydata==1) {
				    	    	     flag=1;  //标识位!!!
							}else {
								flag=0;
								// 提交到后台去
								$.ajax({
									url :'carxl.do?method=updName',
									dataType : 'json',
									data : {
										xname : value,
										xid : id
									},
									type : 'post',
									success : function(mydata) {
										if (mydata == 1) {
											layer.msg('修改成功!!', {icon : 1});
										}
										  //刷新父页
									     $("#tab").bootstrapTable('refresh');
									}
								});
							}
						}
					});
					if (flag==1) {
						return '此名称已经存在！';
					}
				}
			}

		});

	});
}


function getChangeAName() {
	$("body").on('click', '#tab .aname', function() {
		// 第一步：得到你要修改对象的主键值
		var id = $(this).parent().parent().find("td:eq(1)").text();
		
		/*******************得到填充下拉框的内容****************/
		 //得到下拉框
		$.ajax({
			url:'cartype.do?method=cartypeAll',
			dataType:'json',
			data:'',
			type:'post',
			success:function(mydata)
			{
				 var xdata="";
				 var str='';
				 str+="[";
				 $.each(mydata,function(index,xx){
					str+="{ value: "+xx.value+", text:\""+xx.text+"\"},";
				 });	
				 xdata=str.substring(0,str.length-1);
			     xdata+="]";
			     $("#dept").val(xdata);
			}
		});
		/*************************************************/
		
		
		// 第二步：弹出一个修改层
		$(this).editable({
			type : "select", // 编辑框的类型。支持text|textarea|select|date|checklist等
			title : "修改汽车品牌", // 编辑框的标题
			disabled : false, // 是否禁用编辑
			emptytext : "空文本", // 空值的默认文本
			source: strToJson($("#dept").val()), //动态形成下拉框
			mode : "popup", // 编辑框的模式：支持popup和inline两种模式，默认是popup
			validate : function(value) {
				if (value==null) {
					return '对不起，请选择汽车品牌'
				} else {
					// 提交到后台去
					$.ajax({
						url :'carxl.do?method=updName',
						dataType : 'json',
						data : {
							aid : value,
							xid : id
						},
						type : 'post',
						success : function(mydata) {
							if (mydata == 1) {
								layer.msg('修改成功!!', {icon : 1});
							}
							  //刷新父页
						     $("#tab").bootstrapTable('refresh');
						}
					});
				}
			}

		});

	});
}

/**********新增汽车系列***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增汽车系列',
			area:['42%','55%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"carxl.do?method=toadd",
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
						url :'carxl.do?method=getByPages', // 请求后台的URL（*）
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
						uniqueId : "did", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								
								{
									field : 'xid',
									align : 'center',
									title : '编号'
								},
								{
									field : 'aname',
									align : 'center',
									title : '汽车品牌',
									formatter : function(value, row, index) {
										var e = "<span  class='aname'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'xname',
									align : 'center',
									title : '名称',
									formatter : function(value, row, index) {
										var e = "<span  class='xname'>"
												+ value + "</span>";
										return e;
									}
								},
								
								{
									title : '操作',
									field : 'xid',
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
function edit(xid){
	layer.open({
		type:2,
		title:'修改汽车系列',
		area:['42%','55%'],
		shadeClose:true,
		skin:'layui-layer-lan',
		maxmin:true,
		content:"carxl.do?method=carxlOne&xid="+xid,
		end:function(){
			$("#tab").bootstrapTable('refresh');
		}
	});
}

