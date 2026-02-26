package com.vms.vms_backend.dto;

public class DashboardResponseDTO {

    private long totalVisitsToday;
    private long pendingApprovals;
    private long activeVisitors;
    private long completedToday;
    private long availableCards;

    public DashboardResponseDTO(long totalVisitsToday,
                                long pendingApprovals,
                                long activeVisitors,
                                long completedToday,
                                long availableCards) {
        this.totalVisitsToday = totalVisitsToday;
        this.pendingApprovals = pendingApprovals;
        this.activeVisitors = activeVisitors;
        this.completedToday = completedToday;
        this.availableCards = availableCards;
    }

    public long getTotalVisitsToday() {
        return totalVisitsToday;
    }

    public long getPendingApprovals() {
        return pendingApprovals;
    }

    public long getActiveVisitors() {
        return activeVisitors;
    }

    public long getCompletedToday() {
        return completedToday;
    }

    public long getAvailableCards() {
        return availableCards;
    }
}