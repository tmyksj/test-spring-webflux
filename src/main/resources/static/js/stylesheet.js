(function() {
    window.addEventListener("DOMContentLoaded", function(event) {
        for (let item of document.querySelectorAll("input, select, textarea")) {
            updateLabelDirty(item.parentElement, item);
        }
    });

    document.addEventListener("input", function(event) {
        updateLabelDirty(event.target.parentElement, event.target);
    });

    function updateLabelDirty(label, target) {
        if (label === undefined
                || label === null
                || "LABEL" !== label.tagName
                || target === undefined
                || target === null
                || !["INPUT", "TEXTAREA", "SELECT"].includes(target.tagName)) {
            return;
        }

        if (target.value !== "") {
            label.classList.add("dirty");
        } else {
            label.classList.remove("dirty");
        }
    }
})();
