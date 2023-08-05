var rid ;
var pwd = "",mpwd="";
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	seach();
	myadd();
	myexit();
	
	// 分页并查询
	getAllByPage();
	yd();
	$(".fixed-table-container").hide();
	$("#mycont").hide();
	// 行内修改购买数量
	getChangeTcount();
});
function getChangeTcount() {
	$("body").on('click', '#tab .fdw', function() {
		var flag;
		var oldDname = $(this).parent().parent().find("td:eq(5)").text();
		// 第一步：得到你要修改对象的主键值
		var id = $(this).parent().parent().find("td:eq(0)").text();

		// 第二步：弹出一个修改层
		$(this).editable({
			type : "text", // 编辑框的类型。支持text|textarea|select|date|checklist等
			title : "修改购买数量", // 编辑框的标题
			disabled : false, // 是否禁用编辑
			emptytext : "空文本", // 空值的默认文本
			//source: strToJson($("#dj").val()), //动态形成下拉框
			mode : "popup", // 编辑框的模式：支持popup和inline两种模式，默认是popup
			validate : function(value) {
				if (value.length==0) {
					return '请填写购买数量'
				}else if (value==oldDname) {
					layer.msg('修改成功!!', {icon : 1});
					$("#tab").bootstrapTable('refresh');
				} else if (!sz.test(value)||value==0) {
					return '格式有误！';
				} else {
					$.ajax({
						url :'outcp.do?method=outcpUpd',
						dataType : 'json',
						data : {
							tcount : value,
							tid : id
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

// 查询
function getAllByPage() {

	$('#tab').bootstrapTable(
			{
				url :  'outcp.do?method=getOutcp', // 请求后台的URL（*）
				method : 'get', // 请求方式（*）
				dataType : 'json',
				toolbar : '#toolbar', // 工具按钮用哪个容器
				striped : true, // 是否显示行间隔色
				pagination : true, // 是否显示分页（*）
				queryParams : function(params) {
					var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
						limit : params.limit, // 页面大小(每一页要显示多少条)
						offset : params.offset,
						rid : rid
					// 从数据库第几条记录开始
					};
					return temp;
				},// 传递参数（*）

				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pageNumber : 1, // 初始化加载第一页，默认第一页
				pageSize : 10, // 每页的记录行数（*）
				pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
				showColumns : true, // 是否显示所有的列
				showRefresh : true, // 是否显示刷新按钮
				minimumCountColumns : 1, // 最少允许的列数
				clickToSelect : true, // 是否启用点击选中行

				
				uniqueId : "tid", // 每一行的唯一标识，一般为主键列
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				cardView : false, // 是否显示详细视图
				detailView : false, // 是否显示父子表
				onLoadSuccess: function(data){    
					if(data.total==0){
						$("#btn_exit").attr("disabled","disabled");
					}else{
						$("#btn_exit").removeAttr("disabled");
					}
			    },
				columns : [
					{
						field : 'tid',
						title : '编号',
						align : 'center'
					},
					{
						field : 'cname',
						title : '产品类型',
						align : 'center'
					},
					{
						field : 'fname',
						title : '产品名称',
						align : 'center'
					},
					{
						field : 'foutprice',
						title : '出售价格（元）',
						align : 'center'
					},
					{
						field : 'tzk',
						title : '折扣价格（元）',
						align : 'center'
					},
					{
						field : 'fdw',
						title : '购买数量',
						align : 'center',
						formatter : function(value, row, index) {
							var e = "<span  class='fdw'>"
									+ value + "</span>";
							return e;
						}
					},
					{
						field : 'txj',
						title : '小计（元）',
						align : 'center'
					},
					{
						title : '操作',
						field : 'tid',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a href="#" class="btn btn-info btn-xs" onclick="edit(\'' + value
									+ '\')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;编辑</a> ';
							return e;
						}
					}]
			});
}

//搜索框
function seach() {
	$("#seabtn").click(function() {
		var t=$("#rcard").val();
		if(t.length==0){
			layer.tips('对不起，卡号不能为空','#rcard',{tips:[2,'red']});
		}else {
			var index = layer.msg('搜索中,请稍后...', {
				icon: 16,
				shade: 0.2
			});
		}
		
		setTimeout(function() {
			parent.layer.close(index);
			pwd = "";
			mpwd = "";
			var card = $("#rcard").val();
			$.ajax({
				url : "chong.do?method=getOne",
				dataType : "json",
				async:false,
				data : {
					"rcard" : card
				},
				type : "post",
				success : function(mydata) {
					if (mydata.rid == -1) {
						layer.msg('查询为空，请核实后重试！', {
							icon : 3,
							anim : 6
						});			
						$("#btn_add").attr("disabled","disabled");
						$("#btn_exit").attr("disabled","disabled");
						$("#mycont").hide();
						$("#card1").val("---");
						$("#card").html("");
						$("#rname").html("");
						$("#sex").html("");
						$("#rpwd").html("");
						$("#dname").html("");
						$("#rbirthday").html("");
						$("#rtel").html("");
						$("#rmoney").html("");
						$("#flag").html("");
						$("#rjf").html("");
						$("#rcarnum").html("");
						$("#aname").html("");
						$("#xname").html("");
						$("#rway").html("");
						$("#raddress").html("");
						$("#zname").html("");
						$("#rnum").html("");
						$("#rtime").html("");
						$("#rremark").html("");
						$(".fixed-table-container").hide();
					} else {
						$("#rpwd").empty();
						pwd = mydata.rpwd;
						for (var i = 0; i < pwd.length; i++) {
							$("#rpwd").append("*");
						}
						mpwd = $("#rpwd").html();
						$("#btn_add").removeAttr("disabled");
						$("#btn_exit").removeAttr("disabled");
						rid = mydata.rid;
						$("#myimg").attr("src","http://localhost:8080/"+mydata.rimg);
						$("#rid").html(mydata.rid);
						$("#card").html(mydata.rcard);
						$("#rname").html(mydata.rname);
						$("#sex").html(mydata.sex);
						$("#dname").html(mydata.dname);
						$("#rbirthday").html(mydata.rbirthday);
						$("#rtel").html(mydata.rtel);
						$("#rmoney").html(mydata.rmoney);
						$("#flag").html(mydata.flag);
						$("#rjf").html(mydata.rjf);
						$("#rcarnum").html(mydata.rcarnum);
						$("#aname").html(mydata.aname);
						$("#xname").html(mydata.xname);
						$("#rway").html(mydata.rway);
						$("#raddress").html(mydata.raddress);
						$("#zname").html(mydata.zname);
						$("#rnum").html(mydata.rnum);
						$("#rtime").html(mydata.rtime);
						$("#rremark").html(mydata.rremark);
						$("#mycont").show();
						$('#tab').bootstrapTable('refresh');
						$(".fixed-table-container").show();
						layer.photos({
							photos: '#mimg'
							,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
						});
						img();
					}
				}
			})
		}, 100);	
	});
};


// 添加
function myadd() {
	$("#btn_add").click(function() {
		layer.open({
			type:2,
			title:'购买商品',
			area:['50%', '80%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"outcp.do?method=toadd&rid="+rid,
			end:function(){
				$("#tab").bootstrapTable('refresh');
			}
		});
	});
}

//修改
function edit(tid){
	layer.open({
		type:2,
		title:'修改购物信息',
		area:['50%','80%'],
		shadeClose:true,
		skin:'layui-layer-lan',
		maxmin:true,
		content:"outcp.do?method=outcpOne&tid="+tid,
		end:function(){
			$("#tab").bootstrapTable('refresh');
		}
	});
}

/*****结算******/
function myexit() {
	$("#btn_exit").click(function() {
		layer.open({
			type:2,
			title:'结账',
			area:['80%', '95%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"outcp.do?method=toexit&rid="+rid,
			end:function(){
				$("#tab").bootstrapTable('refresh');
				$("#seabtn").click();
			}
		});
	});
}


function yd(){
	$("#rpwd").hover(function (){  
        $("#rpwd").html(pwd); 
    },function (){  
    	$("#rpwd").html(mpwd); 
    });  
}

function img(){
	$("#rimg").click(function(){
		$("#myimg").click();
	});
}