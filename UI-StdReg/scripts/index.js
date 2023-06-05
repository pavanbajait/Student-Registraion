

/*   Add Details with save button  */

async function addDetails(){
    document.querySelector("#submit").addEventListener("click",save);
   async function save(e){
        e.preventDefault();

        var detail={
            affilation:document.querySelector("#affilation").value,
            firstName:document.querySelector("#fname").value,
            lastName:document.querySelector("#lname").value,
            gender:document.querySelector("#gender").value,
            email:document.querySelector("#email").value,
            mobileNo:document.querySelector("#mobile").value,
            dob:document.querySelector("#dob").value,
            city:document.querySelector("#city").value,
           
        };
     
    
            let url=`http://localhost:8080/addDetails`
            let dataPost = (detail)=>{

            fetch(url,{
                method:"POST",
                body:detail,
                headers:{
                "Content-Type":"application/json"
                    }
            }).then(res=>{
                return res;
            }).then(res=>{
                 console.log(res);
                let email=document.querySelector("#email").value;
                userId(email);
            }).then(err=>{
                console.log(err);
            })

            }

         detail=JSON.stringify(detail);   
        dataPost(detail);
       
        
                
        let userId = (email)=>{
            fetch(`http://localhost:8080/getUserId/${email}`,{
            method:"GET",
            headers:{
            "Content-Type":"application/json"
                }
        }).then(res=>{
            return res.json();
        }).then(res=>{
             console.log(res);
             let user = res;
             let url2 = `http://localhost:8080/upload/${user}`;

             let photoPost = (formData)=>{
     
                 fetch(url2,{
                     method:"POST",
                     body:formData,
                     headers:{
                     "Content-Type":"application/json"
                         }
                 }).then(res=>{
                     return res.json();
                 }).then(res=>{
                      console.log(res);
                     
                 }).then(err=>{
                     console.log(err);
                 })
     
                 }
             let userfile = document.getElementById("photo").files[0];
             let formData = new FormData();
             formData.append('user-file', userfile, 'user-file.png');
             console.log(formData,"form");

        }).then(err=>{
            console.log(err);
        })
        }
        location.reload();
    }
}
// location.reload();
// Populate api data on display
fetch("http://localhost:8080/retrieveData").then((data)=>{
    return data.json();
}).then((objData)=>{
    // console.log(objData[0].userId);
    let tableData="";
    objData.map((values)=>{
        tableData+=`<tr id=${values.userId}>
            <td>${values.userId}</td>
            <td>${values.affilation+" "+values.firstName+" "+values.lastName}</td>
            <td>${values.email}</td>
            <td>${values.mobileNo}</td>
            <td>${values.gender}</td>
            <td>${values.dob}</td>
            <td>${values.city}</td>
            <td><a id="upd" style=" color:blue; cursor:pointer; ">${"Update"}</a></td>
            <td><a id="dlt" style=" color:blue; cursor:pointer; ">${"Delete"}</a></td>
        </tr>`;
        document.getElementById("body").innerHTML=tableData;
    })
});
  
// for update & delete selection
document.getElementById("body").addEventListener("click",(e)=>{
    e.preventDefault();
    let updBtnPresd = e.target.id == "upd";
    let dltBtnPresd = e.target.id == "dlt";


    let iddlt = e.target.parentElement.parentElement.id;
    
    if(updBtnPresd){
        updateDetails(e);
    }else if(dltBtnPresd){
        deleteDetails(iddlt);
    }
    
  })
      


async function getAll(){
    let response = [];
    response=await fetch("http://localhost:8080/retrieveData",{
       method:"GET",
       headers:{
           "Content-Type":"application/json"
       }
   }).then(res=>{
    return res.json();
});
    return response;
    // console.log(response)
}


// for update data
 async function updateDetails(element){
    // console.log(element.firstName);
    // console.log(element);

    document.querySelector("#affilation").value= element.affilation;
    document.querySelector("#fname").value=element.firstName;
    document.querySelector("#lname").value=element.lastName;
    document.querySelector("#gender").value=element.gender;
    document.querySelector("#email").value=element.email;
    document.querySelector("#mobile").value=element.mobileNo;
    document.querySelector("#dob").value=element.dob;
    document.querySelector("#city").value=element.city;
    

    document.querySelector("#submit").addEventListener("click",save);
   async function save(e){
        e.preventDefault();

        var detailup={
            affilation:document.querySelector("#affilation").value,
            firstName:document.querySelector("#fname").value,
            lastName:document.querySelector("#lname").value,
            gender:document.querySelector("#gender").value,
            email:document.querySelector("#email").value,
            mobileNo:document.querySelector("#mobile").value,
            dob:document.querySelector("#dob").value,
            city:document.querySelector("#city").value,
            // professionalSkills:[]
        
        };
     
    
            let urlup=`http://localhost:8080/updateDetails`
            let dataPost = (detailup)=>{

            fetch(urlup,{
                method:"PUT",
                body:detailup,
                headers:{
                "Content-Type":"application/json"
                    }
            }).then(res=>{
                return res;
            }).then(res=>{
                 console.log(res);
                let email=document.querySelector("#email").value;
                userId(email);
            }).then(err=>{
                console.log(err);
            })

            }

         detailup=JSON.stringify(detailup);   
        dataPost(detailup);
       
        
                
        let userId = (email)=>{
            fetch(`http://localhost:8080/getUserId/${email}`,{
            method:"GET",
            headers:{
            "Content-Type":"application/json"
                }
        }).then(res=>{
            return res.json();
        }).then(res=>{
             console.log(res);
             let user = res;
             let url2 = `http://localhost:8080/upload/${user}`;

             let photoPost = (formData)=>{
     
                 fetch(url2,{
                     method:"PUT",
                     body:formData,
                     headers:{
                     "Content-Type":"application/json"
                         }
                 }).then(res=>{
                     return res.json();
                 }).then(res=>{
                      console.log(res);
                     
                 }).then(err=>{
                     console.log(err);
                 })
     
                 }
             let userfile = document.getElementById("photo").files[0];
             let formData = new FormData();
             formData.append('user-file', userfile, 'user-file.png');
             console.log(formData,"form");
            

        }).then(err=>{
            console.log(err);
        })
        }
        location.reload();
    }
}


async function deleteDetails(id){

    let response=await fetch(`http://localhost:8080/deleteDetails/${id}`,{
    method:"DELETE",
    headers:{
        "Content-Type":"application/json"
    }
});
let data=await response.json();
    console.log(data);
    location.reload();
}


