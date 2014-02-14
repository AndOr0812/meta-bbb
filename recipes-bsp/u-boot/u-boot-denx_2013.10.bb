require u-boot.inc

# SPL build
UBOOT_BINARY = "u-boot.img"
UBOOT_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_SYMLINK = "u-boot-${MACHINE}.img"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"
SECTION = "bootloader"

# This revision corresponds to the tag "v2013.10"
# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "183acb700378a8cfc5d50a01a65de93fb2c24586"

PV = "v2013.10+git${SRCPV}"

# No patches for other machines yet
COMPATIBLE_MACHINE = "(beaglebone)"

# File is board-specific, only copy when it will be correct.
FWENV = ""

SRC_URI = "git://git.denx.de/u-boot.git;branch=master"
LICENSE = "CLOSED"

S = "${WORKDIR}/git"

