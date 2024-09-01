import axios from "axios";
import {addShipments} from "../store/actions";

export const importDataApi = (data) => dispatch => {
    axios.post('http://localhost:9000/rest/api/importShipments', data).then((response) => {
        dispatch(addShipments(response.data))
    }).catch((error) => {
        console.log(error);
    })
}