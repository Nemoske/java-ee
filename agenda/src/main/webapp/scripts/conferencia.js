/**
* Validar campos obrigatorios
*
* @author Luan de Freitas Miranda
*/
	function validar(){
		const nomeCTT = furmularioContato.nome.value;
		const telefoneCTT = furmularioContato.telefone.value;
		const emailCTT = furmularioContato.email.value;
		
		
		if(nomeCTT === ""){
			window.alert("Nome não pode estar vazio")
			furmularioContato.nome.focus();
			return false;
		}
		else if(telefoneCTT === ""){
			window.alert("Telefone não pode estar vazio")
			furmularioContato.telefone.focus();
			return false;
		}
		else if(!emailCTT.includes("@")){
			window.alert("Email não pode ser invalido")
			furmularioContato.email.focus();
			return false;
		}
		else{
			window.alert("Contato registrado com sucesso!")
			document.forms["furmularioContato"].submit()
		}
	}
	
	
	
		
		