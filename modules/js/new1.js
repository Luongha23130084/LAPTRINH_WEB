var tenMon = new Array("Ngó Sen", "Nem", "Mực", "Gà", "Lợn");
	var countMon = new Array(0, 0, 0, 0, 0);
	var giaMon= new Array(100000, 121000, 199000, 58000, 63000);
	function congMon(x){		
		switch(x){
			case 0: 
				countMon[x]++;
				document.getElementById("ngoSen").innerHTML= tenMon[x]+" "+countMon[x];
				document.getElementById("ngosen_input").value=countMon[x];
				break;
			case 1: 
				countMon[x]++;
				document.getElementById("nem").innerHTML= tenMon[x]+" "+countMon[x];
				document.getElementById("nem_input").value=countMon[x];
				break;
			case 2: 
				countMon[x]++;
				document.getElementById("muc").innerHTML= tenMon[x]+" "+countMon[x];
				document.getElementById("muc_input").value=countMon[x];
				break;
			case 3: 
				countMon[x]++;
				document.getElementById("ga").innerHTML= tenMon[x]+" "+countMon[x];
				document.getElementById("ga_input").value=countMon[x];
				break;
			case 4: 
				countMon[x]++;
				document.getElementById("lon").innerHTML= tenMon[x]+" "+countMon[x];
				document.getElementById("lon_input").value=countMon[x];
				break;
		}
		tinhTien();
	}
	function huyMon(x){
		switch(x){
			case 0: 
				countMon[x]--;
				if(countMon[x]>0) document.getElementById("ngoSen").innerHTML= tenMon[x]+" "+countMon[x];
				else if(countMon[x]==0) document.getElementById("ngoSen").innerHTML=tenMon[x];
				else {countMon[x]++; document.getElementById("ngoSen").innerHTML=tenMon[x];}
				document.getElementById("ngosen_input").value=countMon[x];
				break;
			case 1: 
				countMon[x]--;
				if(countMon[x]>0) document.getElementById("nem").innerHTML= tenMon[x]+" "+countMon[x];
				else if(countMon[x]==0) document.getElementById("nem").innerHTML=tenMon[x];
				else {countMon[x]++; document.getElementById("nem").innerHTML=tenMon[x];}
				document.getElementById("nem_input").value=countMon[x];
				break;
			case 2: 
				countMon[x]--;
				if(countMon[x]>0) document.getElementById("muc").innerHTML= tenMon[x]+" "+countMon[x];
				else if(countMon[x]==0) document.getElementById("muc").innerHTML=tenMon[x];
				else {countMon[x]++; document.getElementById("muc").innerHTML=tenMon[x];}
				document.getElementById("muc_input").value=countMon[x];
				break;
			case 3: 
				countMon[x]--;
				if(countMon[x]>0) document.getElementById("ga").innerHTML= tenMon[x]+" "+countMon[x];
				else if(countMon[x]==0) document.getElementById("ga").innerHTML=tenMon[x];
				else {countMon[x]++; document.getElementById("ga").innerHTML=tenMon[x];}
				document.getElementById("ga_input").value=countMon[x];
				break;
			case 4: 
				countMon[x]--;
				if(countMon[x]>0) document.getElementById("lon").innerHTML= tenMon[x]+" "+countMon[x];
				else if(countMon[x]==0) document.getElementById("lon").innerHTML=tenMon[x];
				else {countMon[x]++; document.getElementById("lon").innerHTML=tenMon[x];}
				document.getElementById("lon_input").value=countMon[x];
				break;
		}
		tinhTien();
	}
	function tinhTien(){
		var money=0;
		var i=0;
		for(i=0; i<5; i++) money+= countMon[i]*giaMon[i];
		if (money>0) {
			document.getElementById("giaTien").innerHTML="Tổng số tiền thanh toán là: "+money;
			document.getElementById("sum").value=money;
		}
		else{
			document.getElementById("giaTien").innerHTML="Tổng số tiền thanh toán";
			document.getElementById("sum").value=0;
		}
	}
	function getBuoi(buoi){		
		if(buoi=="1")
			document.getElementById("trua").checked = true;
		if(buoi =="2")
			document.getElementById("toi").checked = true;
		
	}