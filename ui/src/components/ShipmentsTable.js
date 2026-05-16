import {Table, Typography} from "antd";

const {Title} = Typography;

export default ({data}) => {
    const shipmentColumns = data.columns.map(column => ({
        title: column,
        dataIndex: column,
        key: column
    }));

    const countrySummaryColumns = [
        {title: "Country", dataIndex: "country", key: "country"},
        {title: "Provider A", dataIndex: "providerA", key: "providerA"},
        {title: "Provider B", dataIndex: "providerB", key: "providerB"},
        {title: "Provider C", dataIndex: "providerC", key: "providerC"}
    ];

    const cherryPickColumns = [
        {title: "Country", dataIndex: "country", key: "country"},
        {title: "Provider A", dataIndex: "providerA", key: "providerA"},
        {title: "Provider B", dataIndex: "providerB", key: "providerB"},
        {title: "Provider C", dataIndex: "providerC", key: "providerC"},
        {title: "Total", dataIndex: "total", key: "total"}
    ];

    return (
        <div className="shipment-table-container">
            <Title level={3}>Shipment Costs</Title>
            <Table
                columns={shipmentColumns}
                dataSource={data.rows}
                rowKey={(record, index) => `${record["Shipment #"] || "shipment"}-${index}`}
            />

            <Title level={3}>Cost Overview per Country and Provider</Title>
            <Table
                columns={countrySummaryColumns}
                dataSource={data.countrySummary}
                rowKey="country"
                pagination={false}
            />

            <Title level={3}>Cherry-Pick Allocation per Country</Title>
            <Table
                columns={cherryPickColumns}
                dataSource={data.cherryPickSummary}
                rowKey="country"
                pagination={false}
            />
        </div>
    );
}