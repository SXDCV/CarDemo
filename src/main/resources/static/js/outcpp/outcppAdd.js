var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]*$/;
var phone=/(^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$)|(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)/;//电话格式
$(function() {
	layui.use(function() {
		var form = layui.form;
		getcp(form);
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
	$("#wname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，消费姓名不能为空！','#wname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('消费姓名'+'格式有误！','#wname',{tips:[2,'red']});
		}
	});
	$("#wtel").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，电话不能为空！','#wtel',{tips:[2,'red']});
		}else if (!phone.test(t)) {
			layer.tips('电话号码'+'格式有误！','#wtel',{tips:[2,'red']});
		}
	});
	$("#wcount").blur(function() {
		var t=$(this).val();
		var fcount=$("#fcount").val();
		if (t.length==0) {
			 layer.tips('对不起，购买数量不能为空！','#wcount',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('购买数量'+'格式有误！','#wcount',{tips:[2,'red']});
		}else if (Number(t)>Number(fcount)) {
			layer.tips('库存数量'+'不足！','#wcount',{tips:[2,'red']});
		}else if (t==0) {
			layer.tips('对不起，购买数量不能为0！','#wcount',{tips:[2,'red']});
		}
	});
	
}

function getBtn() {
	$("#btn").click(function() {
		var fid=$("#fid").val();
		var wname=$("#wname").val();
		var wtel=$("#wtel").val();
		var wcount=$("#wcount").val();
		var fcount=$("#fcount").val();
		if (cid==0) {
			 layer.tips('对不起，产品类型不能为空！','#cd',{tips:[2,'red']});
		}else if (fid==0) {
			 layer.tips('对不起，产品名称不能为空！','#fd',{tips:[2,'red']});
		}else if (wname.length==0) {
			 layer.tips('对不起，消费姓名不能为空！','#wname',{tips:[2,'red']});
		}else if (!zu.test(wname)) {
			layer.tips('消费姓名'+'格式有误！','#wname',{tips:[2,'red']});
		}else if (wtel.length==0) {
			 layer.tips('对不起，电话不能为空！','#wtel',{tips:[2,'red']});
		}else if (!phone.test(wtel)) {
			layer.tips('电话号码'+'格式有误！','#wtel',{tips:[2,'red']});
		}else if (wcount.length==0) {
			 layer.tips('对不起，购买数量不能为空！','#wcount',{tips:[2,'red']});
		}else if (!sz.test(wcount)) {
			layer.tips('购买数量'+'格式有误！','#wcount',{tips:[2,'red']});
		}else if (Number(wcount)>Number(fcount)) {
			layer.tips('库存数量'+'不足！','#wcount',{tips:[2,'red']});
		}else if (wcount==0) {
			layer.tips('对不起，购买数量不能为0！','#wcount',{tips:[2,'red']});
		}else {
			var xx="fid="+fid+"&wcount="+wcount+"&wname="+wname+"&wtel="+wtel;
			$.post("outcpp.do?method=outcppAdd",xx,function(mydata){
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
	});
}