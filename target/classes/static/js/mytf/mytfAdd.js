var flag;
var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	layui.use(function() {
		var form = layui.form;
		getOne(form);
	});
	//获取焦点
	$("#fname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});

function getOne(form) {
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
			getfaddress();
	});
}

/*****失去焦点事件******/
function getBlur() {
	$("#fname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，产品名称不能为空！','#fname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('产品名称'+'格式有误！','#fname',{tips:[2,'red']});
		}
	});
	$("#fdw").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，产品单位不能为空！','#dw',{tips:[2,'red']});
		}else {
			getfaddress();
		}
	});
	$("#faddress").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，产品产地不能为空！','#faddress',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('产品产地'+'格式有误！','#faddress',{tips:[2,'red']});
		}else {
			getfaddress();
		}
	});
	$("#foutprice").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，出售价格不能为空！','#foutprice',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('出售价格'+'格式有误！','#foutprice',{tips:[2,'red']});
		}
	});
	$("#finprice").blur(function() {
		var t=$(this).val();
		var foutprice=$("#foutprice").val();
		if (t.length==0) {
			 layer.tips('对不起，进货价格不能为空！','#finprice',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('进货价格'+'格式有误！','#finprice',{tips:[2,'red']});
		}else if (Number(t)>=Number(foutprice)) {
			layer.tips('对不起，进货价格不能大于出售价格！','#finprice',{tips:[2,'red']});
		}
	});
	
}
function getfaddress() {
	var faddress=$("#faddress").val();
	var fname=$("#fname").val();
	var cid=$("#cid").val();
	if(fname.length!=0&&cid!=0&&faddress.length!=0){
		$.ajax({
			url:"mytf.do?method=getCkName",
			data:{"fname":fname,"cid":cid,"faddress":faddress},
			dataType:'json',
			type:'post',
			success:function(mydata){
				if (mydata==1) {
					   layer.tips('对不起，此产品已经存在！','#faddress',{tips:[2,'red']});
	    	    	     flag=1;  //标识位!!!
				}else {
					flag=0;
				}
			}
		});
	}else {
		flag=0;
	}
	
}


/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var fname=$("#fname").val();
		var cid=$("#cid").val();
		var fdw=$("#fdw").val();
		var faddress=$("#faddress").val();
		var foutprice=$("#foutprice").val();
		var finprice=$("#finprice").val();
		var cimg=$("#cimg").val();
		if (fname.length==0) {
			 layer.tips('对不起，产品名称不能为空！','#fname',{tips:[2,'red']});
		}else if (!zu.test(fname)) {
			layer.tips('产品名称'+'格式有误！','#fname',{tips:[2,'red']});
		}else if (cid==0) {
			 layer.tips('对不起，产品类型不能为空！','#cd',{tips:[2,'red']});
		}else if (fdw==0) {
			 layer.tips('对不起，产品单位不能为空！','#dw',{tips:[2,'red']});
		}else if (faddress.length==0) {
			 layer.tips('对不起，产品产地不能为空！','#faddress',{tips:[2,'red']});
		}else if (!zu.test(faddress)) {
			layer.tips('产品产地'+'格式有误！','#faddress',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，此产品已存在！','#faddress',{tips:[2,'red']});
		}else if (foutprice.length==0) {
			 layer.tips('对不起，出售价格不能为空！','#foutprice',{tips:[2,'red']});
		}else if (!sz.test(foutprice)) {
			layer.tips('出售价格'+'格式有误！','#foutprice',{tips:[2,'red']});
		}else if (finprice.length==0) {
			 layer.tips('对不起，进货价格不能为空！','#finprice',{tips:[2,'red']});
		}else if (!sz.test(finprice)) {
			layer.tips('进货价格'+'格式有误！','#finprice',{tips:[2,'red']});
		}else if (Number(finprice)>=Number(foutprice)) {
			layer.tips('对不起，进货价格不能大于出售价格！','#finprice',{tips:[2,'red']});
		}else {
			$.ajaxFileUpload({
				url:"mytf.do?method=mytfAdd",
				secureuri:false,
				fileElementId:['cimg'],
				data:{'fname':fname,'cid':cid,'fdw':fdw,'faddress':faddress,'foutprice':foutprice,'finprice':finprice},
				success:function(mydata,status){
					parent.layer.msg('添加成功!',{icon:6,time:3000});
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				},
				error:function(mydata,status,e){
					parent.layer.msg('添加失败!',{icon:6,time:3000});
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		}
	});
}
