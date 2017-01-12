package test;

public class Secret {
	public static String getSecret(String token){
        if(token!=null && !token.equals("")){
            StringBuffer secret=new StringBuffer();
            Integer secretNum=0;
            for(int k=0;k<16;k++){
                secretNum+=Integer.parseInt(token.substring(k*2, (k+1)*2), 16);
            }
            System.out.println("secretNum: "+secretNum);
            for(int i=0;i<secretNum.toString().length();i++){
                int index=Integer.parseInt(secretNum.toString().substring(i, i+1));
                secret.append(token.substring(index, index+1));
            }
            return secret.toString(); 
        }else{
            return null;
        }
    }


	public static void main(String[] args) {
		System.out.println(getSecret("fe7fdf9cf6dd426b899a6e69364c8a0a"));
	}
}
