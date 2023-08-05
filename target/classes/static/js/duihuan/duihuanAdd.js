var rid;
var flag;
var sz=/^[0-9]*$/;
$(function() {
	layui.use(function() {
		var layer = layui.layer, 
		form = layui.form, 
		table = layui.table;
		//获取服务类型
		getType(form);
		$(".myc").hide();
		$(".mycc").hide();
		getRcard();
	})
	getBlur();
	getBtn();
	
});

/******获取兑换礼品******/
function getType(form) {
	$.ajax({
		url : "lipin.do?method=lipinAll",
		data : '',
		dataType : 'json',
		type : 'post',
		success : function(mydata) {
				$.each(mydata, function(index, xx) {
					$("#nid").append("<option value=" + xx.nid + ">" + xx.nname+ "</option>")
				});
			form.render();
		}
	});
	form.on('select(nid)', function (data) {
		var nid=$("#nid").val();
		$("#hcount").val("");
		$("#sjf").val("");
		if (nid==0) {
			$(".mycc").hide();
			layer.tips('对不起，兑换礼品不能为空！','#nd',{tips:[2,'red']});
			
		}else {
			$.ajax({
				url : "lipin.do?method=lipinOne",
				data : {"nid":nid},
				dataType : 'json',
				type : 'post',
				async:false,
				success : function(mydata) {
					$("#nid").val(mydata.nid);
					$("#njf").val(mydata.njf);
					$("#nncount").val(mydata.nncount);
				}
			});
			$(".mycc").show();
		}
		
	});
}

function getRcard() {
	$("#rcard").blur(function() {
		var rcard=$(this).val();
		$("#hcount").val("");
		$("#sjf").val("");
		if (rcard.length==0) {
			layer.tips('对不起，会员卡号不能为空！','#rcard',{tips:[2,'red']});
		}else {
			$.ajax({
				url : "member.do?method=getCkName",
				data : {"rcard":rcard},
				dataType : 'json',
				type : 'post',
				success : function(mydata) {
					if (mydata==0) {
						flag=1
						$(".myc").hide();
						layer.tips('对不起，会员卡号不存在！','#rcard',{tips:[2,'red']});
					}else {
						getOne();
						$(".myc").show();
					}
					
				}
			});
		}
		
	});
}

function getOne() {
	var rcard=$("#rcard").val();
	$.ajax({
		url : "member.do?method=getAll",
		data : {"rcard":rcard},
		dataType : 'json',
		type : 'post',
		async:false,
		success : function(mydata) {
			$("#rid").val(mydata.rid);
			$("#rname").val(mydata.rname);
			$("#rjf").val(mydata.rjf);
		}
	});
}


function getBlur() {
	
		
	$("#hcount").blur(function() {
		var t=$(this).val();
		var njf=$("#njf").val();
		var rjf=$("#rjf").val();
		var nncount=$("#nncount").val();
		if (t==0) {
			layer.tips('对不起，兑换数量不能为空！','#hcount',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('兑换数量'+'格式有误！','#hcount',{tips:[2,'red']});
		}else if (Number(t)>Number(nncount)) {
			layer.tips('兑换所需礼品库存不足！','#hcount',{tips:[2,'red']});
		}else if (Number(t)*Number(njf)>Number(rjf)) {
			layer.tips('兑换所需积分不能大于积分余额！','#sjf',{tips:[2,'red']});
			var sjf=Number(rjf)-Number(t)*Number(njf);
			$("#sjf").val(sjf);
		}else {
			var sjf=Number(rjf)-Number(t)*Number(njf);
			$("#sjf").val(sjf);
		}
	});
}


function getBtn() {
	$("#btn").click(function() {
		var rcard=$("#rcard").val();
		var nid=$("#nid").val();
		var rid=$("#rid").val();
		var sid=$("#sid").val();
		var njf=$("#njf").val();
		var rjf=$("#rjf").val();
		var sjf=$("#sjf").val();
		var hcount=$("#hcount").val();
		if (rcard.length==0) {
			layer.tips('对不起，会员卡号不能为空！','#rcard',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，会员卡号不存在！','#rcard',{tips:[2,'red']});
		}else if (nid==0) {
			layer.tips('对不起，兑换礼品不能为空！','#nd',{tips:[2,'red']});
		}else if (hcount.length==0) {
			layer.tips('对不起，兑换数量不能为空！','#hcount',{tips:[2,'red']});
		}else if (!sz.test(hcount)) {
			layer.tips('兑换数量'+'格式有误！','#hcount',{tips:[2,'red']});
		}else if (Number(hcount)>Number(nncount)) {
			layer.tips('兑换所需礼品库存不足！','#hcount',{tips:[2,'red']});
		}else if (Number(hcount)*Number(njf)>Number(rjf)) {
			layer.tips('兑换所需积分不能大于积分余额！','#hcount',{tips:[2,'red']});
		}else {
			var xx="nid="+nid+"&rid="+rid+"&hcount="+hcount+"&sjf="+sjf;
			$.post("duihuan.do?method=duihuanAdd",xx,function(mydata){
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

