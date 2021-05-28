package br.com.stock.quote.manager.config.validacao;

public class ErroDto {
	private String campo;
	private String erro;
	
	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

	public ErroDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}
