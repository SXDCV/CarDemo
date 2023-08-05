var rid;
var flag;
$(function() {
	layui.use(function() {
		var layer = layui.layer, 
		form = layui.form, 
		table = layui.table;
		$(".mycc").hide();
		//获取焦点
		$("#rcard").focus();
		getRcard();
	})
	getBlur();
	getBtn();
	
});



function getRcard() {
	$("#rcard").blur(function() {
		var rcard=$(this).val();
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
						$(".mycc").hide();
						layer.tips('对不起，会员卡号不存在！','#rcard',{tips:[2,'red']});
					}else {
						getOne();
						$(".mycc").show();
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
			$("#rtel").val(mydata.rtel);
		}
	});
}


function getBlur() {
	$("#kremark").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			layer.tips('对不起，关怀备注不能为空！','#kremark',{tips:[2,'red']});
		}
	});
}


function getBtn() {
	$("#btn").click(function() {
		var rcard=$("#rcard").val();
		var rid=$("#rid").val();
		var kremark=$("#kremark").val();
		if (rcard.length==0) {
			layer.tips('对不起，会员卡号不能为空！','#rcard',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，会员卡号不存在！','#rcard',{tips:[2,'red']});
		}else if (kremark.length==0) {
			layer.tips('对不起，兑换数量不能为空！','#kremark',{tips:[2,'red']});
		}else {
			var xx="rid="+rid+"&kremark="+kremark;
			$.post("look.do?method=lookAdd",xx,function(mydata){
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

