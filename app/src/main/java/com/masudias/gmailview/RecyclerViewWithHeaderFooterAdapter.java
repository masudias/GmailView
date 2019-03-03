package com.masudias.gmailview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;

import static com.masudias.gmailview.Utilities.DEMO_LIST_SIZE;

public class RecyclerViewWithHeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_VIEW = 1;
    private static final int GROUPED_VIEW = 2;
    private static final int EXPANDED_VIEW = 3;

    private ArrayList<Integer> positionTracker; // Take any list that matches your requirement.
    private Context context;
    private ZoomListener zoomListener;

    // Define a constructor
    public RecyclerViewWithHeaderFooterAdapter(Context context, ZoomListener zoomListener) {
        this.context = context;
        this.zoomListener = zoomListener;
        positionTracker = Utilities.populatePositionsWithDummyData();
    }

    // Define a ViewHolder for Header view
    public class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    // Define a ViewHolder for Expanded view
    public class ExpandedViewHolder extends ViewHolder {
        public ExpandedViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    // Define a ViewHolder for Expanded view
    public class GroupedViewHolder extends ViewHolder {
        public GroupedViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    // And now in onCreateViewHolder you have to pass the correct view
    // while populating the list item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        if (viewType == EXPANDED_VIEW) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_expanded, parent, false);
            ExpandedViewHolder vh = new ExpandedViewHolder(v);
            return vh;
        } else if (viewType == HEADER_VIEW) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_header, parent, false);
            HeaderViewHolder vh = new HeaderViewHolder(v);
            return vh;
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_grouped, parent, false);
            GroupedViewHolder vh = new GroupedViewHolder(v);
            return vh;
        }
    }

    // Now bind the ViewHolder in onBindViewHolder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (holder instanceof ExpandedViewHolder) {
                ExpandedViewHolder vh = (ExpandedViewHolder) holder;
                vh.bindExpandedView(position);
            } else if (holder instanceof GroupedViewHolder) {
                GroupedViewHolder vh = (GroupedViewHolder) holder;
            } else if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder vh = (HeaderViewHolder) holder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Now the critical part. You have return the exact item count of your list
    // I've only one footer. So I returned data.size() + 1
    // If you've multiple headers and footers, you've to return total count
    // like, headers.size() + data.size() + footers.size()

    @Override
    public int getItemCount() {
        return DEMO_LIST_SIZE; // Let us consider we have 6 elements. This can be replaced with email chain size
    }

    // Now define getItemViewType of your own.
    @Override
    public int getItemViewType(int position) {
        if (positionTracker.get(position).equals(HEADER_VIEW)) {
            // This is where we'll add the header.
            return HEADER_VIEW;
        } else if (positionTracker.get(position).equals(GROUPED_VIEW)) {
            // This is where we'll add the header.
            return GROUPED_VIEW;
        } else if (positionTracker.get(position).equals(EXPANDED_VIEW)) {
            // This is where we'll add the header.
            return EXPANDED_VIEW;
        }

        return super.getItemViewType(position);
    }

    // So you're done with adding a footer and its action on onClick.
    // Now set the default ViewHolder for NormalViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Define elements of a row here
        public ViewHolder(View itemView) {
            super(itemView);
            // Find view by ID and initialize here
        }

        public void bindExpandedView(final int position) {
            // bindExpandedView() method to implement actions
            final WebView webView = itemView.findViewById(R.id.email_details_web_view);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.loadUrl("file:///android_asset/sample.html");
            webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    zoomListener.onZoomListener(position);
                }
            });
        }
    }
}