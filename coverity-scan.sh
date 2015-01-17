#!/bin/sh -e

# Runs a Coverity scan on the local directory (which must be in a
# DesignPatterns-scan source-tree root) and submits it.

# The following variables must be set manually based on local machine directory structure:
TOKEN=5qfopYzjggQ3AEE227Gojw
EMAIL=saumilpatel1@gmail.com
COVTOOL=/Users/saumilpatel/Tools/cov-analysis

if [ ! -r paths.mk ]; then
	echo "Cannot find 'paths.mk', run in the root of a configured DesignPatterns source tree!"
	exit 1
fi

VERSION=`  grep '^VERSION ='   paths.mk | sed 's/^VERSION = *//'`
PUBLISHED=`grep '^PUBLISHED =' paths.mk | sed 's/^PUBLISHED = *//'`

git_dirty()
{
	[ "$(git status 2> /dev/null | tail -n1)" != "nothing to commit (working directory clean)" ] && echo -n '*'
}
git_branch()
{
	git branch --no-color 2> /dev/null | sed -e '/^[^*]/d' -e "s/* \(.*\)/\1/"
}
git_commit()
{
	git rev-parse HEAD | cut -c -12
}

if [ "$PUBLISHED" = release ]; then
	DESC="release"
elif [ -n "$PUBLISHED" ]; then
	DESC="snapshot $PUBLISHED"
elif [ -d .git ]; then
	DESC="git: $(git_branch)$(git_dirty) $(git_commit)"
else
	DESC="unknown source on `date`"
fi

export PATH="$PATH:$COVTOOL/bin"

cov-build --dir cov-int mvn -DskipTests=true compile

ARCHIVE=DesignPatterns-scan.tar.xz

echo "Compressing scan directory 'cov-int' into '$ARCHIVE'..."

tar -czf "$ARCHIVE" cov-int

echo "Submitting '$VERSION' '$DESC'"

TMP=`mktemp -d /tmp/curl-cov-submit-XXXXXX.html`

curl --form token="$TOKEN" \
  --form project=design-patterns
  --form email="$EMAIL" \
  --form file=@"$ARCHIVE" \
  --form version="$VERSION" \
  --form description="$VERSION - $DESC" \
  -o $TMP https://scan.coverity.com/builds?project=SaumilP%2Fdesign-patterns

cat $TMP

rm -f $TMP

exit 0
