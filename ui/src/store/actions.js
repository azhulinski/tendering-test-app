export const ADD_SHIPMENTS = "ADD_SHIPMENTS";

export function addShipments(shipments) {
    return {
        type: ADD_SHIPMENTS,
        payload: shipments
    }
}