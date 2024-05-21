export default function ItemList({ items, displayItemFn, onItemClicked }) {
    if (items === undefined || items === null || displayItemFn === undefined || displayItemFn === null) {
        return null;
    }

    return <div>
        {
            items.length === 0
                ? 'no items found'
                : items.map((item, index) => <div key={index} onClick={() => onListItemClicked(item)}>{displayItemFn(item)}</div>)
        }
    </div>

    function onListItemClicked(item) {
        if (onItemClicked !== undefined && onItemClicked !== null) {
            onItemClicked(item);
        }
    }
}