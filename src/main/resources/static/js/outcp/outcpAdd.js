var rid;
var tid;
var sz=/^[0-9]*$/;
$(function() {
	layui.use(function() {
		var form = layui.form;
		getcp(form);
		rid=$("#rid").val();
		
	});
	
	getBlur();
	getBtn();
	
})


function getcp(form) {
	$("#cid").empty();
	$("#cid").append("<option value='0'>---请选择产品类型---</option>");
	$.ajax({
		url:'cptype.do?method=cptypeAll',
		data:'',
		async:false,
		dataType:'json',
		type:'post',
		success:function(mydata){
			$.each(mydata,function(index,xx){
				$("#cid").append("<option value='"+xx.cid+"'>"+xx.cname+"</option>");
			});
			form.render('select');
		}
	});
	form.on('select(cid)', function (data) {
		
		var cid=$("#cid").val();
		$("#fid").empty();
		$("#fid").append("<option value='0'>---请选择产品名称---</option>");
		$.ajax({
			url:'mytf.do?method=mytfFname',
			data:{"cid":cid},
			async:false,
			dataType:'json',
			type:'post',
			success:function(mydata){
				$.each(mydata,function(index,xx){
					$("#fid").append("<option value="+xx.fid+">"+xx.fname+" - "+"(产地 : "+xx.faddress+")"+"</option>");
				});
				form.render('select');
			}
		});
	});
	form.on('select(fid)', function (data) {
		var fid=$("#fid").val();
		$.ajax({
			url:'mytf.do?method=mytfAll',
			data:{"fid":fid},
			async:false,
			dataType:'json',
			type:'post',
			success:function(mydata){
				$("#fcount").val(mydata.fcount);
				$("#foutprice").val(mydata.foutprice);
				
			}
		});
	});
	
}



function getBlur() {
	$("#cid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，产品类型不能为空！','#cd',{tips:[2,'red']});
		}
	});
	$("#fid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，产品名称不能为空！','#fd',{tips:[2,'red']});
		}
	});
	$("#tcount").blur(function() {
		var t=$(this).val();
		var fcount=$("#fcount").val();
		if (t.length==0) {
			 layer.tips('对不起，购买数量不能为空！','#tcount',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('购买数量'+'格式有误！','#tcount',{tips:[2,'red']});
		}else if (Number(t)>Number(fcount)) {
			layer.tips('库存数量'+'不足！','#tcount',{tips:[2,'red']});
		}else if (t==0) {
			layer.tips('对不起，购买数量不能为0！','#tcount',{tips:[2,'red']});
		}
	});
}

function getBtn() {
	$("#btn").click(function() {
		var rid=$("#rid").val();
		var cid=$("#cid").val();
		var fid=$("#fid").val();
		var tcount=$("#tcount").val();
		var fcount=$("#fcount").val();
		if (cid==0) {
			 layer.tips('对不起，产品类型不能为空！','#cd',{tips:[2,'red']});
		}else if (fid==0) {
			 layer.tips('对不起，产品名称不能为空！','#fd',{tips:[2,'red']});
		}else if (tcount.length==0) {
			 layer.tips('对不起，购买数量不能为空！','#tcount',{tips:[2,'red']});
		}else if (!sz.test(tcount)) {
			layer.tips('购买数量'+'格式有误！','#tcount',{tips:[2,'red']});
		}else if (Number(tcount)>Number(fcount)) {
			layer.tips('库存数量'+'不足！','#tcount',{tips:[2,'red']});
		}else if (tcount==0) {
			layer.tips('对不起，购买数量不能为0！','#tcount',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:'outcp.do?method=getCkName',
				data:{
					"rid":rid,
					"fid":fid
					},
				async:false,
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						$.ajax({
							url:'outcp.do?method=gettid',
							data:{
								"rid":rid,
								"fid":fid
								},
							async:false,
							dataType:'json',
							type:'post',
							success:function(mydata){
								tid=mydata.tid;
								$("#tid").val(mydata.tid);
								
							}
						});
						var tid=$("#tid").val();
						var xx="tcount="+tcount+"&tid="+tid;
						$.post("outcp.do?method=addOutcp",xx,function(mydata){
							if (mydata==1) {
								//增加成功！
								   layer.msg('增加成功！', {icon : 1,time : 60000});
					    		       setTimeout(function() {
					    		    	  var index = parent.layer.getFrameIndex(window.name); 
						    		      parent.layer.close(index);
					    		    	},
					    		    	2000);
					    		   }
					       },'json');
					}else {
						var xx="rid="+rid+"&fid="+fid+"&tcount="+tcount;
						$.post("outcp.do?method=outcpAdd",xx,function(mydata){
							if (mydata==true) {
								//增加成功！
								   layer.msg('添加成功！', {icon : 1,time : 60000});
					    		       setTimeout(function() {
					    		    	  var index = parent.layer.getFrameIndex(window.name); 
						    		      parent.layer.close(index);
					    		    	},
					    		    	2000);
					    		   }
					       },'json');
					}
				}
			});
			
		}
	});
}