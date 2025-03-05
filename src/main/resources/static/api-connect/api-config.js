export const BASE_URL = "http://localhost:2222/api";

export function getTokenFromCookie(name = "ctdt") {
	
  const value = `; ${document.cookie}`;
  console.log("name: " + name , "cookie: ", value)
  const parts = value.split(`; ${name}=`);
  console.log("parts: ",parts)
  if (parts.length == 2){
	  
			  
	  return parts.pop().split(';').shift();
  } 
}
