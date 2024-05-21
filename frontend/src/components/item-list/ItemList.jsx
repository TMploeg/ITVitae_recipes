export default function ItemList({ items, displayItemFn }) {
    if (items === undefined || items === null || displayItemFn === undefined || displayItemFn === null) {
        return null;
    }

    return <div>
        {
            items.length === 0
                ? 'no items found'
                : items.map((item, index) => <div key={index}>{displayItemFn(item)}</div>)
        }
    </div>

}