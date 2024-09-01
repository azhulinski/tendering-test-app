import {ADD_SHIPMENTS} from "./actions";

export function shipmentsReducer(state = {shipments: {}}, action) {
    switch (action.type) {
        case ADD_SHIPMENTS:
            return {
                ...state,
                shipments: action.payload
            }
        default:
            return state;
    }
}