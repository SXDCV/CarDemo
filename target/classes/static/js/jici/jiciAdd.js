var rid;
var flag;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	layui.use(function() {
		var layer = layui.layer, 
		form = layui.form, 
		table = layui.table;
		//获取服务类型
		getType(form);
		$(".mycc").hide();
		getRcard();
		getBlur();
		getBtn();
		
	})
});

/******获取服务类型******/
function getType(form) {
	$.ajax({
		url : "servicetype.do?method=getOne",
		data : '',
		dataType : 'json',
		type : 'post',
		success : function(mydata) {
			$.each(mydata, function(index, xx) {
				$("#sid").append("<option value=" + xx.sid + ">" + xx.sname+ "</option>")
			});
			form.render();
		}
	});
}

function getRcard() {
	$("#rcard").blur(function() {
		var rcard=$(this).val();
		$.ajax({
			url : "member.do?method=getCkName",
			data : {"rcard":rcard},
			dataType : 'json',
			type : 'post',
			success : function(mydata) {
				if (mydata==1) {
					getOne();
					$(".mycc").show();
				}else {
					flag=1
					$(".mycc").hide();
					layer.tips('对不起，会员卡号不存在！','#rcard',{tips:[2,'red']});
				}
				
			}
		});
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
			$("#rmoney").val(mydata.rmoney);
		}
	});
}


function getBlur() {
	$("#sid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			layer.tips('对不起，服务类型不能为空！','#sd',{tips:[2,'red']});
		}
	});
	$("#jmoney").blur(function() {
		var t=$(this).val();
		var rmoney=$("#rmoney").val();
		if (t==0) {
			layer.tips('对不起，消费金额不能为空！','#jmoney',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('消费金额'+'格式有误！','#jmoney',{tips:[2,'red']});
		}else if (Number(t)>Number(rmoney)) {
			layer.tips('消费金额不能大于余额！','#jmoney',{tips:[2,'red']});
		}
	});
}


function getBtn() {
	$("#btn").click(function() {
		var rcard=$("#rcard").val();
		var rid=$("#rid").val();
		var sid=$("#sid").val();
		var rmoney=$("#rmoney").val();
		var jmoney=$("#jmoney").val();
		if (rcard.length==0) {
			layer.tips('对不起，会员卡号不能为空！','#rcard',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，会员卡号不存在！','#rcard',{tips:[2,'red']});
		}else if (sid==0) {
			layer.tips('对不起，服务类型不能为空！','#sd',{tips:[2,'red']});
		}else if (jmoney==0) {
			layer.tips('对不起，消费金额不能为空！','#jmoney',{tips:[2,'red']});
		}else if (!sz.test(jmoney)) {
			layer.tips('消费金额'+'格式有误！','#jmoney',{tips:[2,'red']});
		}else if (Number(jmoney)>Number(rmoney)) {
			layer.tips('消费金额不能大于余额！','#jmoney',{tips:[2,'red']});
		}else {
			var xx="rid="+rid+"&sid="+sid+"&jmoney="+jmoney;
			$.post("jici.do?method=jiciAdd",xx,function(mydata){
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

