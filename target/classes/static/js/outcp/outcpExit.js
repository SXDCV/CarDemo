var rid;
$(function() {
	rid=$("#rid").val();
	getBylPages();
	getBtn();
	
});

function getBylPages() {
	$('#tab').bootstrapTable(
			{
				url :'outcp.do?method=getBylPages', // 请求后台的URL（*）
				method : 'get', // 请求方式（*）
				dataType : 'json',
				toolbar : '#toolbar', // 工具按钮用哪个容器
				striped : true, // 是否显示行间隔色
				pagination : true, // 是否显示分页（*）

				queryParams : function(params) {
					var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
						pages : params.limit, // 页面大小(每一页要显示多少条)
						begin : params.offset,
						rid:rid
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
				height : $(window).height()-110, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
				uniqueId : "tid", // 每一行的唯一标识，一般为主键列
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				cardView : false, // 是否显示详细视图
				detailView : false, // 是否显示父子表
				columns : [
						
						{
							field : 'tid',
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
							align : 'center',
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
							align : 'center'
						},
						{
							field : 'txj',
							title : '小计（元）',
							align : 'center'
						},
						{
							field : 'uname',
							title : '经办人',
							align : 'center'
						}]
			});
}

function getBtn() {
	$("#btn").click(function() {
		var rmoney=$("#rmoney").val();
		var handle=$("#handle").val();
		var txj=$("#txj").val();
		if (Number(rmoney)<Number(txj)) {
			layer.alert('余额不足，请充值！',{icon: 5, title:'错误信息',skin: 'layui-layer-lan'});
		}else {
				var list = $("#tab").bootstrapTable('getData');
				var totalNum = $("#tab").bootstrapTable('getOptions').totalRows;
				var alltid="";
				for (var i = 0; i < totalNum; i++) {
					alltid+=list[i].tid+"、";
				}
				$.ajax({
					url:"outcp.do?method=toAlltid",
					data:{
						"alltid":alltid,
						"txj":txj,
					},
					dataType:'json',
					type:'post',
					async:false,
					success:function(mydata){
						if(mydata.rows==1){
							layer.msg("结算成功!",{icon:1});
							setTimeout(function() {
			    		    	  var index = parent.layer.getFrameIndex(window.name); 
				    		      parent.layer.close(index);
			    		    	},
			    		    	1000);
						}else {
							layer.alert("提交失败，数量超出库存。<br>错误原因："+mydata.rows,{icon: 5, title:'错误信息',skin: 'layui-layer-lan'});
						}
					}
				});
		}
	});
	
}