package exesoft;

public class Osoba {
	String imie;
	int wzrost;
	int wiek;
	
	
	public Osoba(String imie, int wzrost, int wiek){
		this.imie = imie;
		this.wzrost = wzrost;
		this.wiek = wiek;
	}
	
	public boolean isEquals(Osoba os2){
		if(this.wiek == os2.wiek && this.wzrost == os2.wzrost)
			return true;
		else
			return false;
	}
}
