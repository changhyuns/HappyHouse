import { coronaInstance } from "./index";

const api = coronaInstance();

function getCoronaList(success, fail){
    const params = {
        serviceKey:"TU2zk/xaOip2v+zo/0teaiWJrIdtlqt+uMyckRIYlFnqxKp+sPstPieQcEWf2iTPCT9GsLNAYMRMUCc3YCP0GA==",
        page:1,
        perPage:284,
    }

    api.get(``, { params: params }).then(success).catch(fail);
}

export {getCoronaList};