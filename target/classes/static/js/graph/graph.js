var title = [],json = [],cont = [];
var did = 0;
$(function() {	
	getAllByPage();
	layui.use('element', function(){
		var element = layui.element;
		element.on('tab(demo)', function(data){
			did = $(this).val();
			$("#mydj").html($(this).html());
			$('#tab').bootstrapTable('refresh');
		});
	
	});
	Myinit();
	chart();
});

function Myinit(){
	$.ajax({
		url : "graph.do?method=chart",
		dataType : "json",
		data : '',
		async: false,
		type : "post",
		success : function(mydata) {
			$.each(mydata, function(index, xx) {
				var row = {value:xx.aid,name:xx.dname};
				title[index] = xx.dname;
				cont[index] = xx.aid;
				json.push(row);
			});
		}
	})
}

function chart() {

	var myChart = echarts.init(document.getElementById('main'));
	var myChart1 = echarts.init(document.getElementById('main1'));
	
	var option1 = {
				color: ['#3398DB'],
	            title: {
	                text: '会员等级人数表'
	            },
	            tooltip: {
	            	trigger : 'item',
	    			formatter : "{a} <br/>{b} : {c}人 "
	            },
	            legend: {
	                data:['人数']
	            },
	            xAxis: {
	                data: title
	            },
	            yAxis: {},
	            series: [{
	                name: '人数',
	                type: 'bar',
	                data: cont
	            }]
	        };

	myChart1.setOption(option1);
	
	var option = {
		title : {
			text : '会员等级人数图',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}人 ({d}%)"
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			data : title
		},
		series : [ {
			name : '等级人数',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : json,
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};

	myChart.setOption(option);
}

// 查询
function getAllByPage() {
	$('#tab').bootstrapTable(
					{
						url : 'graph.do?method=djall', // 请求后台的URL（*）
						method : 'get', // 请求方式（*）
						dataType : 'json',
						striped : true, // 是否显示行间隔色
						pagination : true, // 是否显示分页（*）
						toolbar : '#toolbar', // 工具按钮用哪个容器
						queryParams : function(params) {
							var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
								pages : params.limit, // 页面大小(每一页要显示多少条)
								begin : params.offset,
								did : did
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
						uniqueId : "rid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								{
									checkbox : true
								},
								{
									field : 'rid',
									title : '编号',
									align : 'center'
								},
								{
									field : 'rcard',
									title : '会员卡号',
									align : 'center',
								},
								{
									field : 'rname',
									title : '会员姓名',
									align : 'center'
								},
								{
									field : 'raddress',
									title : '联系地址',
									align : 'center'
								},
								{
									field : 'sex',
									title : '会员性别',
									align : 'center'
								},
								{
									field : 'dname',
									title : '会员等级',
									align : 'center'
								},
								{
									field : 'rmoney',
									title : '卡上余额',
									align : 'center'
								},
								{
									field : 'rjf',
									title : '剩余积分',
									align : 'center'
								},
								{
									title : '会员详情',
									field : 'rid',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a href="#" onclick="data(\''
												+ value
												+ '\')"><i class="layui-icon layui-icon-link"></i>&nbsp;详情</a>';
										return e;
									}
								} ]
					});
}




// 详情
function data(rid) {
	layer.open({
		type:2,
		title:'查看会员信息',
		area:['60%','90%'],
		shadeClose:true,
		skin:'layui-layer-lan',
		maxmin:true,
		content:"member.do?method=getOne&rid="+rid,
		end:function(){
			$("#tab").bootstrapTable('refresh');
		}
	});
}
