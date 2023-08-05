var flag;
var flag1;
var flag2;
var flag3;
var time;
var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
var number=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;//车牌号
var phone=/(^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$)|(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)/;//电话格式
var card = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//身份证号
$(function() {
	layui.use( function() {
		var form = layui.form,
		laydate=layui.laydate;
		getOne(form);
		getblur(form);
		
		laydate.render({ 
			  elem: '#rbirthday'
			  ,format: 'yyyy-MM-dd' //可任意组合
			});
	});
		//获取焦点
		$("#rcard").focus();
		//失去焦点
		getBlur();
		gettime();
		//提交
		getBtn();
		
	
	
});



function gettime() {
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var date=date.getDate();
	
	time=year+"-"+month+"-"+date;
}
function getblur(form) {
	form.on('select(aid)', function (data) {
		var aid=$("#aid").val();
		$("#xid").empty();
		$("#xid").append("<option value='0'>---请选择汽车系列---</option>");
			$.ajax({
				url:'carxl.do?method=getOne',
				data:{"aid":aid},
				async:false,
				dataType:'json',
				type:'post',
				success:function(mydata){
					$.each(mydata,function(index,xx){
						$("#xid").append("<option value='"+xx.xid+"'>"+xx.xname+"</option>");
					});
					form.render('select');
				}
			});
	
	});
}


/*******获取下拉框*********/
function getOne(form) {
	$("#did").empty();
	$("#did").append("<option value='0'>---请选择会员等级---</option>");
	$.ajax({
		url:'dj.do?method=djAll',
		data:'',
		async:false,
		dataType:'json',
		type:'post',
		success:function(mydata){
			$.each(mydata,function(index,xx){
				$("#did").append("<option value='"+xx.did+"'>"+xx.dname+"</option>");
			});
			form.render('select');
		}
	});
	$.ajax({
		url:'cartype.do?method=cartypeAll',
		data:'',
		async:false,
		dataType:'json',
		type:'post',
		success:function(mydata){
			$.each(mydata,function(index,xx){
				$("#aid").append("<option value='"+xx.aid+"'>"+xx.aname+"</option>");
			});
			form.render('select');
		}
	});
	$.ajax({
		url:'pz.do?method=pzAll',
		data:'',
		async:false,
		dataType:'json',
		type:'post',
		success:function(mydata){
			$.each(mydata,function(index,xx){
				$("#zid").append("<option value='"+xx.zid+"'>"+xx.zname+"</option>");
			});
			form.render('select');
		}
	});
}

/*****失去焦点事件******/
function getBlur() {
	$("#rcard").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，会员卡号不能为空！','#rcard',{tips:[2,'red']});
		}else {
			$.ajax({
				url:"member.do?method=getCkName",
				data:{"rcard":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此会员卡号已经存在！','#rcard',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}
	});
	$("#rname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，会员名称不能为空！','#rname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('会员名称'+'格式有误！','#rname',{tips:[2,'red']});
		}
	});
	
	$("#rtel").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，手机号码不能为空！','#rtel',{tips:[2,'red']});
		}else if (!phone.test(t)) {
			layer.tips('手机号码'+'格式有误！','#rtel',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:"member.do?method=getCkName",
				data:{"rtel":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此手机号码已经存在！','#rtel',{tips:[2,'red']});
		    	    	     flag1=1;  //标识位!!!
					}else {
						flag1=0;
					}
				}
			});
		}
	});
	
	$("#rpwd").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，密码不能为空！','#rpwd',{tips:[2,'red']});
		}
	});
	
	$("#did").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，会员等级不能为空！','#dd',{tips:[2,'red']});
		}
	});
	$("#rcolor").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，汽车颜色不能为空！','#rcolor',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('汽车颜色'+'格式有误！','#rcolor',{tips:[2,'red']});
		}
	});
	$("#rcarnum").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，汽车牌号不能为空！','#rcarnum',{tips:[2,'red']});
		}else if (!number.test(t)) {
			layer.tips('汽车牌号'+'格式有误！','#rcarnum',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:"member.do?method=getCkName",
				data:{"rcarnum":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此汽车牌号已经存在！','#rcarnum',{tips:[2,'red']});
		    	    	     flag2=1;  //标识位!!!
					}else {
						flag2=0;
					}
				}
			});
		}
	});
	$("#aid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，汽车品牌不能为空！','#ad',{tips:[2,'red']});
		}
	});
	$("#xid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，汽车系列不能为空！','#xd',{tips:[2,'red']});
		}
	});
	$("#zid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，证件类型不能为空！','#zd',{tips:[2,'red']});
		}
	});
	$("#rnum").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，证件编号不能为空！','#rnum',{tips:[2,'red']});
		}else if (!card.test(t)) {
			layer.tips('证件编号'+'格式有误！','#rnum',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:"member.do?method=getCkName",
				data:{"rnum":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此证件编号已经存在！','#rnum',{tips:[2,'red']});
		    	    	     flag3=1;  //标识位!!!
					}else {
						flag3=0;
					}
				}
			});
		}
	});
	$("#rway").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，行驶里程不能为空！','#rway',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('行驶里程'+'格式有误！','#rway',{tips:[2,'red']});
		}
	});
	$("#raddress").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，联系地址不能为空！','#raddress',{tips:[2,'red']});
		}
	});
	
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var rcard=$("#rcard").val();
		var rname=$("#rname").val();
		var rpwd=$("#rpwd").val();
		var ximg=$("#ximg").val();
		var rtel=$("#rtel").val();
		var rsex=$("#rsex").val();
		var did=$("#did").val();
		var rbirthday=$("#rbirthday").val();
		var rcarnum=$("#rcarnum").val();
		var aid=$("#aid").val();
		var xid=$("#xid").val();
		var rcolor=$("#rcolor").val();
		var rway=$("#rway").val();
		var zid=$("#zid").val();
		var rnum=$("#rnum").val();
		var raddress=$("#raddress").val();
		var rremark=$("#rremark").val();
		
		if (rcard.length==0) {
			 layer.tips('对不起，会员卡号不能为空！','#rcard',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，会员卡号已存在！','#rcard',{tips:[2,'red']});
		}else if (rname.length==0) {
			layer.tips('对不起，会员姓名不能为空！','#rname',{tips:[2,'red']});
		}else if (!zu.test(rname)) {
			layer.tips('会员名称'+'格式有误！','#rname',{tips:[2,'red']});
		}else if (rtel.length==0) {
			layer.tips('对不起，电话不能为空！','#rtel',{tips:[2,'red']});
		}else if (flag1==1) {
			layer.tips('对不起，电话已存在！','#rtel',{tips:[2,'red']});
		}else if (!phone.test(rtel)) {
			layer.tips('手机号码'+'格式有误！','#rtel',{tips:[2,'red']});
		}else if (rpwd.length==0) {
			layer.tips('对不起，密码不能为空！','#rpwd',{tips:[2,'red']});
		}else if (did==0) {
			layer.tips('对不起，会员等级不能为空！','#dd',{tips:[2,'red']});
		}else if (rbirthday.length==0) {
			 layer.tips('对不起，会员生日不能为空！','#rbirthday',{tips:[2,'red']});
		}else if (rcolor.length==0) {
			 layer.tips('对不起，汽车颜色不能为空！','#rcolor',{tips:[2,'red']});
		}else if (!zu.test(rcolor)) {
			layer.tips('汽车颜色'+'格式有误！','#rcolor',{tips:[2,'red']});
		}else if (rcarnum.length==0) {
			 layer.tips('对不起，汽车牌号不能为空！','#rcarnum',{tips:[2,'red']});
		}else if (!number.test(rcarnum)) {
			layer.tips('汽车牌号'+'格式有误！','#rcarnum',{tips:[2,'red']});
		}else if (flag2==1) {
			layer.tips('对不起，汽车牌号已存在！','#rcarnum',{tips:[2,'red']});
		}else if (aid==0) {
			 layer.tips('对不起，汽车品牌不能为空！','#ad',{tips:[2,'red']});
		}else if (xid==0) {
			 layer.tips('对不起，汽车系列不能为空！','#xd',{tips:[2,'red']});
		}else if (zid==0) {
			 layer.tips('对不起，证件类型不能为空！','#zd',{tips:[2,'red']});
		}else if (rnum.length==0) {
			 layer.tips('对不起，证件编号不能为空！','#rnum',{tips:[2,'red']});
		}else if (!card.test(rnum)) {
			layer.tips('证件编号'+'格式有误！','#rnum',{tips:[2,'red']});
		}else if (flag3==1) {
			layer.tips('对不起，证件编号已存在！','#rnum',{tips:[2,'red']});
		}else if (rway.length==0) {
			 layer.tips('对不起，行驶里程不能为空！','#rway',{tips:[2,'red']});
		}else if (!sz.test(rway)) {
			layer.tips('行驶里程'+'格式有误！','#rway',{tips:[2,'red']});
		}else if (raddress.length==0) {
			 layer.tips('对不起，联系地址不能为空！','#raddress',{tips:[2,'red']});
		}else {
			$.ajaxFileUpload({
				url:"member.do?method=memberAdd",
				secureuri:false,
				fileElementId:['ximg'],
				data:{'rcard':rcard,'rname':rname,'rpwd':rpwd,'rtel':rtel,'rsex':rsex,'did':did,'rbirthday':rbirthday,'rcarnum':rcarnum,'xid':xid,'rcolor':rcolor,'rway':rway,'zid':zid,'rnum':rnum,'raddress':raddress,'rremark':rremark,'rtime':time},
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
