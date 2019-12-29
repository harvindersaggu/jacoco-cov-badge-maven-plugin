package io.github.handofgod94.domain;

public class LineCoverage extends Coverage implements CoverageInterface {

  LineCoverage(CoverageCategory category, Report report) {
    super(category, report);
  }

  @Override
  public long calculateMissed() {
    return report.getLines().map(ReportLine::getLineMissed).sum().longValue();
  }

  @Override
  public long calculateCovered() {
    return report.getLines().map(ReportLine::getLineCovered).sum().longValue();
  }
}
