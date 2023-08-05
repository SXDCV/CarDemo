var arr = new Array(); // 声明一维数组
var myr = [[0,'会员卡号'],[0,'优惠活动'],[0,'充值金额'],[0,'赠送金额'],[0,'最终金额'],[0,'充值备注']];
var number=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
var ylessmoney;
$(function() {
	layui.use(function() {
		var form = layui.form;
		getyouhui(form);
	
	});
	$("#rcard").focus();
	$("#mycont").hide();
	$("#btn").hide();
	seach();
	getBlur();
	getBtn();
})

function getmoney() {
	var omoney=$("#omoney").val();
	var osmoney=$("#osmoney").val();
		$("#olastmoney").val(Number(omoney)+Number(osmoney));

	
}


function getyouhui(form) {
	$("#yid").empty();
	$("#yid").append("<option value='0'>--请选择优惠活动--</option>");
	$.ajax({
		url:'youhui.do?method=youhuiAll',
		data:'',
		dataType:'json',
		type:'post',
		async:false,
		success:function(mydata){
			$.each(mydata,function(index,x){
				$("#yid").append("<option value='"+x.yid+"'>"+x.ytitle+"</option>");
			});
			form.render('select');
		}
	});
	form.on('select(yid)', function (data) {
		var yid=$("#yid").val();
		$.ajax({
			url:'youhui.do?method=getOne',
			data:{
				"yid":yid,
			},
			dataType:'json',
			type:'post',
			async:false,
			success:function(mydata){
				ylessmoney=mydata.ylessmoney;
				$("#omoney").val(mydata.ylessmoney);
				$("#osmoney").val(mydata.ymoney);
				getmoney();
			}
		});
	});
	$("#omoney").change(function() {
		getmoney();
	});
}


function seach() {
	$("#seabtn").click(function() {
		var t=$("#rcard").val();
		if(t.length==0){
			layer.tips('对不起，卡号不能为空','#rcard',{tips:[2,'red']});
		}
		var index = layer.msg('搜索中,请稍后...', {
			icon: 16
			,shade: 0.2
		});
		setTimeout(function() {
			layer.close(index);
			var card = $("#rcard").val();
			myr[0][0] = 0;
			$("#rid").val(0);
			$.ajax({
				url : "chong.do?method=getOne",
				dataType : "json",
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
						fid = mydata.fid;
						$("#btn").hide();
						$("#btn1").show();
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
					} else {
						myr[0][0] = 1;
						fid = 0;
						$("#rid").val(mydata.rid);
						$("#card1").val(mydata.rcard);
						$("#myimg").attr("src","http://localhost:8080/"+mydata.rimg);
						$("#card").html(mydata.rcard);
						$("#rname").html(mydata.rname);
						$("#sex").html(mydata.sex);
						$("#rpwd").html(mydata.rpwd);
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
						$("#btn").show();
						$("#btn1").hide();
						layer.photos({
							photos: '#mimg'
							,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
						});
						img();
					}
				}
			})
		},800);
	});
};
function getBlur(){
	$("#rcard").blur(function(){
		var t=$(this).val();
		if(t.length==0){
			layer.tips('对不起，卡号不能为空','#rcard',{tips:[2,'red']});
		}
		
	});
	$("#omoney").blur(function(){
		var t=$(this).val();
		if(t.length==0){
			layer.tips('对不起，充值金额不能为空','#omoney',{tips:[2,'red']});
		}else if(!number.test(t)){
			layer.tips('对不起，格式错误','#omoney',{tips:[2,'red']});
		}else if (Number(t)<Number(ylessmoney)){
			layer.tips('充值金额需大于'+ylessmoney+'元','#omoney',{tips:[2,'red']});
		}
	});
	$("#oremark").blur(function(){
		var t=$(this).val();
		if(t.length==0){
			layer.tips('对不起，充值备注不能为空','#oremark',{tips:[2,'red']});
		}
	});
}

function getBtn(){
	$("#btn").click(function(){
		var rid=$("#rid").val();
		var omoney=$("#omoney").val();
		var yid=$("#yid").val();
		var osmoney=$("#osmoney").val();
		var olastmoney=$("#olastmoney").val();
		var oremark=$("#oremark").val();
		if(yid==0){
			layer.tips('对不起，优惠活动不能为空','#yd',{tips:[2,'red']});
		}else if(omoney.length==0){
			layer.tips('对不起，充值金额不能为空','#omoney',{tips:[2,'red']});
		}else if(!number.test(omoney)){
			layer.tips('对不起，格式错误','#omoney',{tips:[2,'red']});
		}else if(Number(omoney)<Number(ylessmoney)){
			layer.tips('充值金额需大于'+ylessmoney+'元','#omoney',{tips:[2,'red']});
		}else if(oremark.length==0){
			layer.tips('对不起，充值备注不能为空','#oremark',{tips:[2,'red']});
		}else{
			var xx="rid="+rid+"&omoney="+omoney+"&yid="+yid+"&osmoney="+osmoney+"&olastmoney="+olastmoney+"&oremark="+oremark;
			$.post("chong.do?method=chongAdd",xx,function(mydata){
				if(mydata==true){
					$.ajax({
						url:'member.do?method=updMoney',
						data:{
							"rid":rid,
							"rmoney":olastmoney,
						},
						dataType:'json',
						type:'post',
						async:false,
						success:function(mydata){
							layer.msg('新增成功！',{icon:1,time:60000});
							setTimeout(function(){
								var index=parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
							},1000);
						}
					});
				}else{
					layer.msg('新增失败！',{icon:1,time:60000});
					setTimeout(function(){
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},1000);
				}
			},'json');
		}
	});
}


function img(){
	$("#rimg").click(function(){
		$("#myimg").click();
	});
}