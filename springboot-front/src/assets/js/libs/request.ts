/*eslint-disable*/
import axios from "axios";
// import {fetch} from 'whatwg-fetch';

export function request(url: string, method = "get", data = {}, config = {}) {
  return axiosRequest(url, method, data, config);
  //return fetchRequest(url, method,data);
}

function axiosRequest(url: string, method: string, data: any, config: any) {
  if (method.toLocaleLowerCase() === "post") {
    let params = new URLSearchParams();
    if (data instanceof Object) {
      for (let key in data) {
        params.append(key, data[key]);
      }
      data = params;
    }
  } else if (method.toLocaleLowerCase() === "file") {
    method = "post";
    let params = new FormData();
    if (data instanceof Object) {
      for (let key in data) {
        params.append(key, data[key]);
      }
      data = params;
    }
  }
  let axiosConfig: any = {
    method: method.toLocaleLowerCase(),
    url: url,
    data: data,
  };
  if (config instanceof Object) {
    for (let key in config) {
      axiosConfig[key] = config[key];
    }
  }
  return axios(axiosConfig).then((res) => res.data);
}

// function fetchRequest(url,method,data) {
//     let fetchConfig={};
//     if (method.toLocaleLowerCase()==='post'){
//         if (data instanceof Object){
//             let body="";
//             for (let key in data){
//                 body+="&"+key+"="+encodeURIComponent(data[key]);
//             }
//             data = body.slice(1);
//         }
//         fetchConfig['body']=data;
//         fetchConfig["headers"]={
//             'Content-Type':"application/x-www-form-urlencoded"
//         };
//     }else if (method.toLocaleLowerCase()==='file'){
//         method = "post";
//         if (data instanceof Object){
//             let param=new FormData();
//             for (let key in data){
//                 param.append(key, data[key]);
//             }
//             data = param;
//         }
//         fetchConfig['body']=data;
//     }
//     fetchConfig['method']=method.toLocaleLowerCase();

//     return fetch(url,fetchConfig).then(res=>res.json());
// }
