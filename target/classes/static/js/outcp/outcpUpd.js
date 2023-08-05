var rid;
var time;
var cid1;
var fid1;
var fcount;
var sz=/^[0-9]*$/;
$(function() {
	layui.use(function() {
		var form = layui.form;
		getcp(form);
		
	});
	rid=$("#rid").val();
	cid1=$("#cid1").val();
	fid1=$("#fid1").val();
	fcount=$("#fcount").val();
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
				if (cid1==xx.cid) {
					$("#cid").append("<option value='"+xx.cid+"' selected>"+xx.cname+"</option>");
					$("#fid").empty();
					$("#fid").append("<option value='0'>---请选择产品名称---</option>");
					$.ajax({
						url:'mytf.do?method=mytfFname',
						data:{"cid":xx.cid},
						async:false,
						dataType:'json',
						type:'post',
						success:function(mydata){
							$.each(mydata,function(index,xx){
								if (fid1==xx.fid) {
									$("#fid").append("<option value="+xx.fid+" selected>"+xx.fname+" - "+"(产地 : "+xx.faddress+")"+"</option>");
									$.ajax({
										url:'mytf.do?method=mytfAll',
										data:{"fid":xx.fid},
										async:false,
										dataType:'json',
										type:'post',
										success:function(mydata){
											$("#fcount").val(mydata.fcount);
											$("#foutprice").val(mydata.foutprice);
											
										}
									});
								}
								if (fid1!=xx.fid) {
									$("#fid").append("<option value="+xx.fid+">"+xx.fname+" - "+"(产地 : "+xx.faddress+")"+"</option>");
								}
								
							});
							form.render('select');
						}
					});
				}
				if (cid1!=xx.cid) {
					$("#cid").append("<option value='"+xx.cid+"'>"+xx.cname+"</option>");
				}
				
			});
			form.render('select');
		}
	});
	
	
}




function getBlur() {
	
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
		var tid=$("#tid").val();
		var fcount=$("#fcount").val();
		var tcount=$("#tcount").val();
		 if (tcount.length==0) {
			 layer.tips('对不起，购买数量不能为空！','#tcount',{tips:[2,'red']});
		}else if (!sz.test(tcount)) {
			layer.tips('购买数量'+'格式有误！','#tcount',{tips:[2,'red']});
		}else if (Number(tcount)>Number(fcount)) {
			layer.tips('库存数量'+'不足！','#tcount',{tips:[2,'red']});
		}else if (tcount==0) {
			layer.tips('对不起，购买数量不能为0！','#tcount',{tips:[2,'red']});
		}else {
			var xx="tcount="+tcount+"&tid="+tid;
			$.post("outcp.do?method=outcpUpd",xx,function(mydata){
				if (mydata==1) {
					//增加成功！
					   layer.msg('修改成功！', {icon : 1,time : 60000});
		    		       setTimeout(function() {
		    		    	  var index = parent.layer.getFrameIndex(window.name); 
			    		      parent.layer.close(index);
		    		    	},
		    		    	2000);
		    		   }
		       },'json');
		}
	});
}