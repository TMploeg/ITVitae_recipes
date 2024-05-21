import "./ItemList.css";

export default function ItemList({ items, displayItemFn, onItemClicked }) {
    if (items === undefined || items === null || displayItemFn === undefined || displayItemFn === null) {
        return null;
    }

    const hasClickFunction = onItemClicked !== undefined && onItemClicked !== null;

    return <div>
        {
            items.length === 0
                ? 'no items found'
                : items.map(
                    (item, index) => <div
                        className={hasClickFunction ? 'clickable-list-item' : ''}
                        key={index}
                        onClick={() => onListItemClicked(item)}>{displayItemFn(item)}</div>
                )
        }
    </div>

    function onListItemClicked(item) {
        if (hasClickFunction) {
            onItemClicked(item);
        }
    }
}