import {Table} from "antd";

export default ({data}) => {

    const columns = data.columns.map(column => ({
        title: column, dataIndex: column, key: column
    }));

    return (
        <div className="shipment-table-container">
            <Table columns={columns} dataSource={data.rows}/>
        </div>)

}