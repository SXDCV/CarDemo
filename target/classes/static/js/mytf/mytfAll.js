$(function() {
	// 分页
	getAll();
	//新增产品信息
	getAdd();
	
	
});

/**********新增产品信息***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增产品信息',
			area:['50%','80%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"mytf.do?method=toadd",
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
						url :'mytf.do?method=getByPages', // 请求后台的URL（*）
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
						uniqueId : "fid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						onLoadSuccess: function(data){    
						       layer.photos({
						        photos: '.cimg'
						        ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
						       }); 
						       img();
						       $(".mg").hide();
						         },
						columns : [
								
								{
									field : 'fid',
									title : '产品编号',
									align : 'center'
								},
								{
									field : 'fname',
									title : '产品名称',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<span class="xname">' + value
												+ '</span>';
										return e;
									}
								},
								{
									field : 'cname',
									title : '产品类别',
									align : 'center'
								},
								{
									field : 'fimg',
									title : '图片',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<div class="cimg"><a href="#" class="tt" id="'+
											index+'"><i class="glyphicon glyphicon-picture" aria-hidden="true"></i>&nbsp;详情</a><img class="mg" id="i'+
											index+'" src="http://localhost:8080/'+value+'"></div>';			
										return e;
									}
								},
								{
									field : 'faddress',
									title : '产地',
									align : 'center'
								},
								{
									field : 'foutprice',
									title : '出售价',
									align : 'center'
								},
								{
									field : 'finprice',
									title : '进货价',
									align : 'center'
								},
								{
									field : 'fdw',
									title : '产品数量',
									align : 'center'
								},
								{
									title : '操作',
									field : 'fid',
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
function edit(fid){
	layer.open({
		type:2,
		title:'修改产品信息',
		area:['50%','80%'],
		shadeClose:true,
		skin:'layui-layer-lan',
		maxmin:true,
		content:"mytf.do?method=mytfOne&fid="+fid,
		end:function(){
			$("#tab").bootstrapTable('refresh');
		}
	});
}
function img(){
	 $(".tt").click(function(){
	  var t = $(this).attr("id");
	  $("#i"+t).click();
	 });
	}
