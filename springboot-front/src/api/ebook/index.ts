import config from "../../assets/js/conf/config";
import { request } from "../../assets/js/libs/request";

export function findAllEbookData() {
  return request(`${config.baseApi}/ebook/findAllEbook`, "get");
}
