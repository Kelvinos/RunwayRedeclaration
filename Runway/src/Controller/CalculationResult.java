package Controller;

public class CalculationResult 
{
	private int updatedTora;
	private int updatedToda;
	private int updatedAsda;
	private int updatedLda;

	public CalculationResult(int tora, int toda, int asda, int lda) 
	{
		setUpdatedAsda(asda);
		setUpdatedLda(lda);
		setUpdatedToda(toda);
		setUpdatedTora(tora);
	}

	public int getUpdatedAsda() {
		return updatedAsda;
	}
	
	public int getUpdatedLda() {
		return updatedLda;
	}
	
	public int getUpdatedToda() {
		return updatedToda;
	}
	
	public int getUpdatedTora() {
		return updatedTora;
	}
	
	public void setUpdatedAsda(int updatedAsda) {
		this.updatedAsda = updatedAsda;
	}
	
	public void setUpdatedLda(int updatedLda) {
		this.updatedLda = updatedLda;
	}
	
	public void setUpdatedToda(int updatedToda) {
		this.updatedToda = updatedToda;
	}
	
	public void setUpdatedTora(int updatedTora) {
		this.updatedTora = updatedTora;
	}
}
